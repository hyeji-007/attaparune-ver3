package com.green.attaparunever2.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class BlackList extends CreatedAt{
    @EmbeddedId
    private BlackListIds blackListIds;

    @ManyToOne
    @MapsId("restaurantId")
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 200)
    private String reason;
}
