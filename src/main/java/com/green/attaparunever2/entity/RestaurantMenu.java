package com.green.attaparunever2.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@ToString
public class RestaurantMenu extends UpdatedAt{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RestaurantMenuCategory categoryId;

    @Column(length = 20, nullable = false)
    private String menuName;

    @Column(length = 100)
    private String menuPic;

    @Column(nullable = false)
    private int price;

    @Column(length = 100)
    private String detail;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int available;

    @Column(nullable = false, columnDefinition = "int default 1")
    private int useYn;

}
