package com.green.attaparunever2.admin.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetRestaurantEnrollmentRes {
    private int totalPageCount;

    private List<SelRestaurantEnrollmentRes> selRestaurantEnrollmentResList;
}
