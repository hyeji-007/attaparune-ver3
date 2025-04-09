package com.green.attaparunever2.order.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetOrderDto {
    @Schema(title = "메뉴 ID")
    private long menuId;

    @Schema(title = "메뉴 이름")
    private String menuName;

    @Schema(title = "메뉴 수량")
    private int menuCount;

    @Schema(title = "메뉴 가격")
    private int menuPrice;
}
