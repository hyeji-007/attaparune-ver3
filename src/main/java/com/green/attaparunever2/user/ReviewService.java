package com.green.attaparunever2.user;

import com.green.attaparunever2.admin.restaurant.ReviewCommentRepository;
import com.green.attaparunever2.common.MyFileUtils;
import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.common.model.Paging;
import com.green.attaparunever2.config.security.AuthenticationFacade;
import com.green.attaparunever2.entity.*;
import com.green.attaparunever2.order.OrderRepository;
import com.green.attaparunever2.order.ticket.TicketRepository;
import com.green.attaparunever2.user.model.GetReviewDto;
import com.green.attaparunever2.user.model.GetReviewReq;
import com.green.attaparunever2.user.model.GetReviewRes;
import com.green.attaparunever2.user.model.ReviewRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewPicRepository reviewPicRepository;
    private final OrderRepository orderRepository;
    private final TicketRepository ticketRepository;
    private final AuthenticationFacade authenticationFacade;
    private final MyFileUtils myFileUtils;
    private final ReviewMapper reviewMapper;
    private final ReviewCommentRepository reviewCommentRepository;

    @Transactional
    public Review postReview(ReviewRequestDto reviewRequestDto, List<MultipartFile> reviewPics) throws IOException {
        // 1. 로그인한 사용자의 userId
        Long userId = authenticationFacade.getSignedUserId();

        // 2. 주문 정보 조회
        Order order = orderRepository.findByOrderId(reviewRequestDto.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("주문 정보가 존재하지 않습니다."));

        // 3. 사용자가 본인 주문에 대해서만 리뷰 작성 가능
        if (!order.getUserId().getUserId().equals(userId)) {
            throw new IllegalArgumentException("본인의 주문에 대해서만 리뷰를 작성할 수 있습니다.");
        }

        // 4. 주문과 연결된 식권 정보 조회
        Ticket ticket = ticketRepository.findByOrder(order)
                .orElseThrow(() -> new IllegalArgumentException("식권 정보가 존재하지 않습니다."));

        // 5. 식권 사용 여부 체크 (ticketStatus가 1이어야 함)
        if (ticket.getTicketStatus() != 1) {
            throw new IllegalArgumentException("식권이 사용되지 않았습니다.");
        }

        // 6. 이미 해당 주문에 리뷰가 작성되었는지 확인
        if (reviewRepository.findByOrderId(order.getOrderId()).isPresent()) {
            throw new IllegalArgumentException("이 식권에 대해서는 이미 리뷰가 작성되었습니다.");
        }

        // 7. 사용자가 식사를 한 식당에 대한 리뷰만 작성 가능
        Restaurant restaurant = order.getRestaurantId(); // 식당 정보 가져오기
        if (restaurant == null) {
            throw new IllegalArgumentException("식사를 한 식당 정보가 없습니다.");
        }

        // 8. 식권 사용일자(UseDate)로부터 24시간 이내에만 리뷰 작성 가능
        LocalDateTime useDate = ticket.getUseDate();
        if (useDate == null) {
            throw new IllegalArgumentException("식권 사용일자가 존재하지 않습니다.");
        }

        // 현재 시간이 사용일자(UseDate)로부터 24시간 이내인지 확인
        if (useDate.plusHours(24).isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("식권 사용 후 24시간이 경과하여 리뷰를 작성할 수 없습니다.");
        }

        // 9. 리뷰 저장
        Review review = new Review();
        review.setOrder(order);
        review.setRating(reviewRequestDto.getRating());
        review.setReviewText(reviewRequestDto.getReviewText());

        // 리뷰 저장 후 Review 엔티티 생성
        review = reviewRepository.save(review);

        // 10. 리뷰 사진 저장
        if (reviewPics != null && !reviewPics.isEmpty()) {
            if (reviewPics.size() > 3) {
                throw new IllegalArgumentException("리뷰 사진은 최대 3개까지 등록할 수 있습니다.");
            }

            for (MultipartFile file : reviewPics) {
                if (!file.isEmpty()) {
                    // 파일 저장 경로 설정
                    String folderPath = "review_pics/" + order.getOrderId();
                    myFileUtils.makeFolders(folderPath);

                    // 랜덤 파일명 생성 및 저장
                    String savedFileName = myFileUtils.makeRandomFileName(file);
                    String filePath = folderPath + "/" + savedFileName;

                    try {
                        myFileUtils.transferTo(file, filePath);
                    } catch (IOException e) {
                        log.error("파일 저장 실패: {}", e.getMessage());
                        throw new RuntimeException("파일 저장 실패", e);
                    }

                    // ReviewPic 엔티티에 추가
                    ReviewPic reviewPic = new ReviewPic();
                    reviewPic.setOrder(order);
                    reviewPic.setReviewPic(savedFileName);

                    reviewPicRepository.save(reviewPic);
                }
            }
        }

        return review;
    }

    @Transactional
    public void delReview(Long orderId) throws IOException {
        Long userId = authenticationFacade.getSignedUserId();

        Order order = orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문 정보가 존재하지 않습니다."));

        if (!order.getUserId().getUserId().equals(userId)) {
            throw new IllegalArgumentException("본인의 주문이 아니므로 리뷰를 삭제할 수 없습니다.");
        }

        Review review = reviewRepository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰 정보가 존재하지 않습니다."));

        // 해당 리뷰에 연결된 리뷰 사진 삭제
        List<ReviewPic> reviewPics = reviewPicRepository.findByOrder_OrderId(orderId);
        for (ReviewPic pic : reviewPics) {
            // 파일 시스템에서 삭제
            String filePath = "review_pics/" + orderId + "/" + pic.getReviewPic();
            myFileUtils.deleteFile(filePath);
            // DB에서 삭제
            reviewPicRepository.delete(pic);
        }

        // 리뷰 댓글 삭제
        reviewCommentRepository.deleteById(review.getOrderId());

        // 리뷰 삭제
        reviewRepository.delete(review);
    }


    @Transactional
    public List<GetReviewRes> getReview(GetReviewReq p)  {

        Long signedUserId = authenticationFacade.getSignedUserId();
        if (signedUserId == null) {
            throw new CustomException("로그인이 필요합니다", HttpStatus.UNAUTHORIZED);
        }
        p.setUserId(signedUserId);

        List<GetReviewDto> reviewDtoList = reviewMapper.getReviewList(p);

        List<GetReviewRes> reviewResList = new ArrayList<>();

        for (GetReviewDto reviewDto : reviewDtoList) {
            Long orderId = reviewDto.getOrderId();

            String picName = reviewMapper.getRestaurantPic(orderId); // 식당 사진 1개
            List<String> menuNames = reviewMapper.getMenuList(orderId); // 주문한 메뉴 리스트
            List<String> reviewPics = reviewMapper.getReviewPicList(orderId); // 리뷰 사진 리스트

            GetReviewRes reviewRes = new GetReviewRes();
            reviewRes.setOrderId(reviewDto.getOrderId());
            reviewRes.setRestaurantName(reviewDto.getRestaurantName());
            reviewRes.setPicName(picName);
            reviewRes.setMenuName(menuNames);
            reviewRes.setRating(reviewDto.getRating());
            reviewRes.setReviewText(reviewDto.getReviewText());
            reviewRes.setCreatedAt(reviewDto.getCreatedAt());
            reviewRes.setRestaurantId(reviewDto.getRestaurantId());
            reviewRes.setCommentText(reviewDto.getCommentText());
            reviewRes.setCommentCreatedAt(reviewDto.getCommentCreatedAt());
            reviewRes.setReviewPic(reviewPics);

            reviewResList.add(reviewRes);
        }

        return reviewResList;
    }
}
