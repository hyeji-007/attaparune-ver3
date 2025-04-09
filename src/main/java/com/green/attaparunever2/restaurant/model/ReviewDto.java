package com.green.attaparunever2.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ReviewDto {
    private long orderId;
    private String nickName;
    private String userPic;
    private int rating;
    private String reviewText;
    private String createdAt;
    private List<String> reviewPic;
    private List<String> menuName;
    private String commentText;
    private String commentCreatedAt;
    private long userId;
}
