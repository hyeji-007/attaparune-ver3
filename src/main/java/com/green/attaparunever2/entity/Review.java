package com.green.attaparunever2.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Review extends UpdatedAt{

    @Id
    private Long orderId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
    private Order order;

    @Min(value = 1, message = "별점은 1점 이상이어야 합니다.")
    @Max(value = 5, message = "별점은 5점 이하이어야 합니다.")
    @Column(nullable = false)
    private int rating;

    @Column(nullable = false, length = 1000)
    private String reviewText;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int reviewStatus;

}
