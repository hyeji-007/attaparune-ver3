package com.green.attaparunever2.restaurant.restaurant_menu;

import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.restaurant.restaurant_menu.model.PostMenuReq;
import com.green.attaparunever2.restaurant.restaurant_menu.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("restaurant/menu")
@Tag(name = "식당 메뉴", description = "식당 메뉴 관리")
public class RestaurantMenuController {
    private final RestaurantMenuService restaurantMenuService;

//    @GetMapping
//    @Operation(summary = "카테고리 PK로 메뉴 보기")
//    public ResultResponse<List<SelMenuRes>> getMenu(@ParameterObject @ModelAttribute SelMenuReq p){
//        List<SelMenuRes> res = restaurantMenuService.getMenu(p);
//
//        return ResultResponse.<List<SelMenuRes>>builder()
//                .statusCode("200")
//                .resultMsg("메뉴 보기 완료")
//                .resultData(res)
//                .build();
//    }
}
