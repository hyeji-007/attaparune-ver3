package com.green.attaparunever2.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "주문 메뉴 리스트")
public class GetUserOrderMenuListDto {
    @Schema(title = "메뉴 PK")
    private long menuId;
    @Schema(title = "주문 상세 PK")
    private long orderDetailId;
    @Schema(title = "메뉴 이름")
    private String menuName;
    @Schema(title = "메뉴 수량")
    private int menuCount;
    @Schema(title = "메뉴별 총 가격")
    private int menuPrice;
}
