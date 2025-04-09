package com.green.attaparunever2.restaurant.restaurant_menu;


import com.green.attaparunever2.common.MyFileUtils;
import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.entity.OrderDetail;
import com.green.attaparunever2.entity.Restaurant;
import com.green.attaparunever2.entity.RestaurantMenu;
import com.green.attaparunever2.entity.RestaurantMenuCategory;
import com.green.attaparunever2.order.OrderRepository;
import com.green.attaparunever2.restaurant.RestaurantRepository;
import com.green.attaparunever2.restaurant.restaurant_menu.model.PostMenuReq;
import com.green.attaparunever2.restaurant.restaurant_menu.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantMenuService {
    private final RestaurantMenuMapper restaurantMenuMapper;
    private final MyFileUtils myFileUtils;
    private final RestaurantMenuRepository restaurantMenuRepository;
    private final RestaurantMenuCategoryRepository restaurantMenuCategoryRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderRepository orderRepository;


    @Transactional
    public int updRestaurantMenu(UpdMenuReq p) {
        RestaurantMenu restaurantMenu = restaurantMenuRepository.findById(p.getMenuId())
                .orElseThrow(() -> new CustomException("메뉴를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        // 카테고리 수정
        if (p.getCategoryId() != 0) {
            RestaurantMenuCategory category = restaurantMenuCategoryRepository.findById(p.getCategoryId())
                    .orElseThrow(() -> new CustomException("카테고리를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));
            restaurantMenu.setCategoryId(category);
        }

        // 메뉴 이름 수정
        if (p.getMenuName() != null && !p.getMenuName().isEmpty()) {
            restaurantMenu.setMenuName(p.getMenuName());
        }

        // 가격 수정
        if (p.getPrice() >= 0) {
            restaurantMenu.setPrice(p.getPrice());
        }

        // 메뉴 설명 수정
        if (p.getDetails() != null) {
            restaurantMenu.setDetail(p.getDetails());
        }

        // 주문 가능 상태 수정 (1 또는 0으로만 수정)
        if (p.getAvailable() == 1 || p.getAvailable() == 0) {
            restaurantMenu.setAvailable(p.getAvailable());
        }

        return 1;
    }

    public int postCategory(PostCategoryReq p) {
        int result = restaurantMenuMapper.postCategory(p);
        return result;
    }

    public int updCategory(UpdCategoryReq p) {
        RestaurantMenuCategory restaurantMenuCategory = restaurantMenuCategoryRepository.findById(p.getCategoryId())
                .orElseThrow(() -> new CustomException("해당 메뉴 카테고리가 없습니다", HttpStatus.BAD_REQUEST));

        restaurantMenuCategory.setCategoryName(p.getCategoryName());
        restaurantMenuCategoryRepository.save(restaurantMenuCategory);
        return 1;
    }

    public int delCategory(DelCategoryReq p) {
        int result = restaurantMenuMapper.delCategory(p);
        return result;
    }

}
