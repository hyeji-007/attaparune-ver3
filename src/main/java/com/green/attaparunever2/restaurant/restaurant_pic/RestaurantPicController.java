package com.green.attaparunever2.restaurant.restaurant_pic;

import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.restaurant.model.InsRestaurantRes;
import com.green.attaparunever2.restaurant.restaurant_pic.model.UpdRestaurantMenuPicReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("pic")
@Tag(name = "사진 수정", description = "식당과 메뉴 사진 수정")
public class RestaurantPicController {
    private final RestaurantPicService restaurantPicService;
}
