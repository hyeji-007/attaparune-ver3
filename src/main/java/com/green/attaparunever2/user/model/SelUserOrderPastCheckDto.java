package com.green.attaparunever2.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelUserOrderPastCheckDto {
    @Schema(description = "메뉴 PK")
    private Long menuId;
    @Schema(description = "메뉴 이름")
    private String menuName;
    @Schema(description = "메뉴 가격")
    private int price;
    @Schema(description = "메뉴 수량")
    private int menuCount;
}
