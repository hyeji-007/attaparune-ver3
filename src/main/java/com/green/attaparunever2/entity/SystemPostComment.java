package com.green.attaparunever2.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class SystemPostComment extends UpdatedAt{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquiryCommentId;

    @OneToOne
    @JoinColumn(name = "inquiryId", nullable = false)
    private SystemPost systemPost;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @Column(length = 500, nullable = false)
    private String commentDetail;
}
