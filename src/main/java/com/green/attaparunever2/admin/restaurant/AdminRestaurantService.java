package com.green.attaparunever2.admin.restaurant;

import com.green.attaparunever2.admin.AdminRepository;
import com.green.attaparunever2.admin.restaurant.model.*;
import com.green.attaparunever2.common.MyFileUtils;
import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.config.security.AuthenticationFacade;
import com.green.attaparunever2.entity.*;
import com.green.attaparunever2.order.OrderRepository;
import com.green.attaparunever2.order.ticket.TicketRepository;
import com.green.attaparunever2.restaurant.RestaurantRepository;
import com.green.attaparunever2.restaurant.restaurant_menu.RestaurantMenuCategoryRepository;
import com.green.attaparunever2.restaurant.restaurant_menu.RestaurantMenuRepository;
import com.green.attaparunever2.restaurant.restaurant_menu.model.DelMenuReq;
import com.green.attaparunever2.restaurant.restaurant_menu.model.PostMenuReq;
import com.green.attaparunever2.user.ReviewRepository;
import com.green.attaparunever2.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminRestaurantService {
    private final AuthenticationFacade authenticationFacade;
    private final AdminRepository adminRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderRepository orderRepository;
    private final ReviewCommentRepository reviewCommentRepository;
    private final ReviewRepository reviewRepository;
    private final BlackListRepository blackListRepository;
    private final UserRepository userRepository;
    private final MyFileUtils myFileUtils;
    private final RestaurantMenuCategoryRepository restaurantMenuCategoryRepository;
    private final RestaurantMenuRepository restaurantMenuRepository;
    private final AdminRestaurantMapper adminRestaurantMapper;

    public int postReviewComment(InsReviewCommentReq req) {
        // 식당 관리자 권한 인증
        Long signedAdminId = authenticationFacade.getSignedUserId();
        if (!signedAdminId.equals(req.getAdminId())) {
            throw new CustomException("로그인한 관리자 계정과 일치하지 않는 관리자 정보입니다.", HttpStatus.BAD_REQUEST);
        }

        Admin admin = adminRepository.findById(req.getAdminId())
                .orElseThrow(() -> new CustomException(("관리자 정보를 찾을 수 없습니다."), HttpStatus.NOT_FOUND));

        Restaurant restaurant = restaurantRepository.findById(admin.getDivisionId())
                .orElseThrow(() -> new CustomException(("식당 정보를 찾을 수 없습니다."), HttpStatus.NOT_FOUND));

        if (!admin.getDivisionId().equals(restaurant.getRestaurantId())) {
            throw new CustomException("식당에 대한 권한이 없습니다.", HttpStatus.BAD_REQUEST);
        }


        Order order = orderRepository.findById(req.getOrderId())
                .orElseThrow(() -> new CustomException("주문 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        if (!admin.getDivisionId().equals(order.getRestaurantId().getRestaurantId())) {
            throw new CustomException("해당 리뷰에 댓글을 작성할 권한이 없습니다.", HttpStatus.BAD_REQUEST);
        }

        ReviewComment reviewComment = new ReviewComment();
        reviewComment.setOrder(order);
        reviewComment.setCommentText(req.getCommentText());
        reviewCommentRepository.save(reviewComment);

        return 1;
    }

    public int patchReviewDelRequest(UpdReviewDelRequestReq req) {
        Long signedAdminId = authenticationFacade.getSignedUserId();
        if (!signedAdminId.equals(req.getAdminId())) {
            throw new CustomException("로그인한 관리자 계정과 일치하지 않는 관리자 정보입니다.", HttpStatus.BAD_REQUEST);
        }

        Admin admin = adminRepository.findById(req.getAdminId())
                .orElseThrow(() -> new CustomException("관리자 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        Order order = orderRepository.findById(req.getOrderId())
                .orElseThrow(() -> new CustomException("주문 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        if (!admin.getDivisionId().equals(order.getRestaurantId().getRestaurantId())) {
            throw new CustomException("해당 리뷰에 삭제 요청할 권한이 없습니다.", HttpStatus.BAD_REQUEST);
        }

        Review review = reviewRepository.findById(req.getOrderId())
                .orElseThrow(() -> new CustomException("리뷰 정보가 없습니다.", HttpStatus.NOT_FOUND));

        review.setReviewStatus(1);
        reviewRepository.save(review);

        return 1;
    }

    public int postBlackList(InsBlackListReq req) {
        // 관리자 로그인 인증
        Long signedAdminId = authenticationFacade.getSignedUserId();
        if (!signedAdminId.equals(req.getAdminId())) {
            throw new CustomException("로그인한 관리자 계정과 일치하지 않는 관리자 정보입니다.", HttpStatus.BAD_REQUEST);
        }

        Admin admin = adminRepository.findById(req.getAdminId())
                .orElseThrow(() -> new CustomException("관리자 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        Restaurant restaurant = restaurantRepository.findById(req.getRestaurantId())
                .orElseThrow(() -> new CustomException("식당 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        // 본인 식당의 관리자가 맞는지 확인 절차
        if (!admin.getDivisionId().equals(restaurant.getRestaurantId())) {
            throw new CustomException("해당 식당의 관리자가 아닙니다.", HttpStatus.BAD_REQUEST);
        }

        // 블랙리스트 등록하려는 사용자가 해당 식당에 주문한 기록이 있는지 확인
        // 아너
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new CustomException("유저 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        List<Order> order = orderRepository.findByRestaurantIdAndUserId(restaurant, user);
        if (order.isEmpty()) {
            throw new CustomException("해당 식당에 대한 주문 내역이 없는 사용자입니다.", HttpStatus.BAD_REQUEST);
        }

        int existingCount = adminRestaurantMapper.selBlackListCount(req.getRestaurantId(), req.getUserId());
        if (existingCount > 0) {
            throw new CustomException("이미 등록된 사용자입니다.", HttpStatus.BAD_REQUEST);
        }

        // BlackListIds 객체 생성 및 값 설정
        BlackListIds blackListIds = new BlackListIds();
        blackListIds.setRestaurantId(restaurant.getRestaurantId()); // restaurantId 설정
        blackListIds.setUserId(user.getUserId()); // userId 설정

        // BlackList 객체 생성 및 값 설정
        BlackList blackList = new BlackList();
        blackList.setBlackListIds(blackListIds); // BlackListIds 설정
        blackList.setRestaurant(restaurant);
        blackList.setUser(user);
        blackList.setReason(req.getReason());
        blackListRepository.save(blackList);

        return 1;
    }

    public int delBlackList(DelBlackListReq req) {
        // 관리자 로그인 인증
        Long signedAdminId = authenticationFacade.getSignedUserId();
        if (!signedAdminId.equals(req.getAdminId())) {
            throw new CustomException("로그인한 관리자 계정과 일치하지 않는 관리자 정보입니다.", HttpStatus.BAD_REQUEST);
        }

        Admin admin = adminRepository.findById(req.getAdminId())
                .orElseThrow(() -> new CustomException("관리자 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        Restaurant restaurant = restaurantRepository.findById(req.getRestaurantId())
                .orElseThrow(() -> new CustomException("식당 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        // 본인 식당의 관리자가 맞는지 확인 절차
        if (!admin.getDivisionId().equals(restaurant.getRestaurantId())) {
            throw new CustomException("해당 식당의 관리자가 아닙니다.", HttpStatus.BAD_REQUEST);
        }

        BlackList blackList = blackListRepository.findByRestaurantIdAndUserId(req.getRestaurantId(), req.getUserId())
                .orElseThrow(() -> new CustomException("블랙리스트 명단에 없는 사용자입니다.", HttpStatus.NOT_FOUND));

        blackListRepository.delete(blackList);

        return 1;
    }

    public int patchPaymentPassword(UpdPaymentPasswordReq req) {
        // 관리자 로그인 인증
        Long signedAdminId = authenticationFacade.getSignedUserId();
        if (!signedAdminId.equals(req.getAdminId())) {
            throw new CustomException("로그인한 관리자 계정과 일치하지 않는 관리자 정보입니다.", HttpStatus.BAD_REQUEST);
        }

        Admin admin = adminRepository.findById(req.getAdminId())
                .orElseThrow(() -> new CustomException("관리자 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        Restaurant restaurant = restaurantRepository.findById(req.getRestaurantId())
                .orElseThrow(() -> new CustomException("식당 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        // 본인 식당의 관리자가 맞는지 확인 절차
        if (!admin.getDivisionId().equals(restaurant.getRestaurantId())) {
            throw new CustomException("해당 식당의 관리자가 아닙니다.", HttpStatus.BAD_REQUEST);
        }

        // 비밀번호 암호화
        String hashedPassword = BCrypt.hashpw(req.getPaymentPassword(), BCrypt.gensalt());

        restaurant.setPaymentPassword(hashedPassword);
        restaurantRepository.save(restaurant);

        return 1;
    }


    // 식당 메뉴 등록
    @Transactional
    public RestaurantMenu postMenu(MultipartFile pic, PostMenuReq p) {
        // 파일 이름 생성
        String savedPicName = null;
        if (pic != null) {
            try {
                savedPicName = myFileUtils.makeRandomFileName(pic);
            } catch (Exception e) {
                throw new RuntimeException("파일 이름 생성 실패", e);
            }
        }
        p.setMenuPic(savedPicName);

        // 식당 정보 가져오기
        Restaurant restaurant = restaurantRepository.findById(p.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("해당 ID의 식당을 찾을 수 없습니다. restaurantId: " + p.getRestaurantId()));

        // 카테고리 조회 또는 생성
        if (p.getCategoryName() == null || p.getCategoryName().trim().isEmpty()) {
            throw new IllegalArgumentException("카테고리 이름이 필요합니다.");
        }

        RestaurantMenuCategory category = restaurantMenuCategoryRepository
                .findByRestaurantAndCategoryName(restaurant, p.getCategoryName())
                .orElseGet(() -> {
                    RestaurantMenuCategory newCategory = new RestaurantMenuCategory();
                    newCategory.setRestaurant(restaurant);
                    newCategory.setCategoryName(p.getCategoryName());
                    return restaurantMenuCategoryRepository.save(newCategory);
                });

        // 메뉴 엔티티 생성 후 저장
        RestaurantMenu menu = new RestaurantMenu();
        menu.setCategoryId(category);
        menu.setMenuName(p.getMenuName());
        menu.setAvailable(p.getAvailable());
        menu.setDetail(p.getDetails());
        menu.setPrice(p.getPrice());
        menu.setMenuPic(savedPicName);

        RestaurantMenu savedMenu = restaurantMenuRepository.save(menu);

        // 파일 저장
        if (pic != null) {
            String middlePath = String.format("menu/%d", savedMenu.getMenuId());
            myFileUtils.makeFolders(middlePath);
            String filePath = String.format("%s/%s", middlePath, savedPicName);
            try {
                myFileUtils.transferTo(pic, filePath);
            } catch (IOException e) {
                throw new RuntimeException("파일 저장 실패", e);
            }
        }

        return savedMenu;
    }

    // 식당 메뉴 삭제
    @Transactional
    public void deleteMenu(DelMenuReq req) {
        Long orderDetailId = orderRepository.findOrderDetailIdByMenuId(req.getMenuId());

        // 메뉴 존재 여부 확인
        RestaurantMenu menu = restaurantMenuRepository.findById(req.getMenuId())
                .orElseThrow(() -> new RuntimeException("해당 메뉴를 찾을 수 없습니다. menuId: " + req.getMenuId()));

        RestaurantMenuCategory restaurantMenuCategory = restaurantMenuCategoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new CustomException("해당 메뉴 카테고리가 없습니다.", HttpStatus.BAD_REQUEST));

        if(!restaurantMenuCategory.getCategoryId().equals(menu.getCategoryId().getCategoryId())) {
            throw new CustomException("해당 카테고리가 아닙니다", HttpStatus.BAD_REQUEST);
        }

        if(!restaurantMenuCategory.getRestaurant().getRestaurantId().equals(req.getRestaurantId())) {
            throw new CustomException("해당 식당이 아닙니다.", HttpStatus.BAD_REQUEST);
        }



        if(orderDetailId == null) {
            // 메뉴 사진 파일 삭제
            String menuPic = menu.getMenuPic();
            if (menuPic != null && !menuPic.isEmpty()) {
                String middlePath = String.format("menu/%d", req.getMenuId());
                String filePath = String.format("%s/%s", middlePath, menuPic);

                try {
                    myFileUtils.deleteFile(filePath);
                } catch (IOException e) {
                    throw new RuntimeException("파일 삭제 실패", e);
                }
            }

            // 메뉴 삭제
            restaurantMenuRepository.deleteById(req.getMenuId());

            if (restaurantMenuRepository.countByCategoryId(restaurantMenuCategory) == 0) {
                restaurantMenuCategoryRepository.deleteById(req.getCategoryId());
            }
        }
        else {
            menu.setUseYn(0);
            restaurantMenuRepository.save(menu);
        }
    }

    //리뷰 댓글 삭제
    @Transactional
    public int delReviewComment(DelReviewCommentReq req) {
        Order order = orderRepository.findById(req.getOrderId())
                .orElseThrow(() -> new CustomException("해당 주문이 없습니다.", HttpStatus.BAD_REQUEST));

        if(!order.getRestaurantId().getRestaurantId().equals(req.getRestaurantId())) {
            throw new CustomException("해당 식당이 아닙니다", HttpStatus.BAD_REQUEST);
        }

        ReviewComment reviewComment = reviewCommentRepository.findById(req.getOrderId())
                .orElseThrow(() -> new CustomException("해당 리뷰 코멘트가 없습니다", HttpStatus.BAD_REQUEST));

        reviewCommentRepository.delete(reviewComment);

        return 1;
    }

    //블랙리스트 조회
    @Transactional
    public List<SelBlackListRes> getBlackList(SelBlackListReq req) {
        Long signedAdminId = authenticationFacade.getSignedUserId();
        if (!signedAdminId.equals(req.getAdminId())) {
            throw new CustomException("로그인한 관리자 계정과 일치하지 않는 관리자 정보입니다.", HttpStatus.BAD_REQUEST);
        }

        Admin admin = adminRepository.findById(req.getAdminId())
                .orElseThrow(() -> new CustomException("해당하는 관리자가 없습니다.", HttpStatus.BAD_REQUEST));
        if (!admin.getDivisionId().equals(req.getRestaurantId())) {
            throw new CustomException("해당 식당에 대한 권한이 없습니다.", HttpStatus.BAD_REQUEST);
        }

        List<SelBlackListRes> list = adminRestaurantMapper.selBlackList(req);
        return list;
    }
}
