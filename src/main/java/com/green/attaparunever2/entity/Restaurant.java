package com.green.attaparunever2.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Restaurant extends UpdatedAt{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private RestaurantCategory categoryId;

    @Column(length = 50, nullable = false)
    private String restaurantName;

    @Column(length = 200, nullable = false)
    private String restaurantAddress;

    @Column(length = 20, nullable = false)
    private String restaurantNumber;

    @Column(nullable = false, length = 20, unique = true)
    private String businessNumber;

    @Column(length = 50)
    private String operatingHours;

    @Column(length = 200)
    private String restaurantDescription;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int status;

    @Column(nullable = false)
    private int maxCapacity;

    @Column(nullable = false)
    private double lat;

    @Column(nullable = false)
    private double lng;

    @Column(length = 100)
    private String paymentPassword;

}
