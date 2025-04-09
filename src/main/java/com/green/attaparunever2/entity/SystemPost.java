package com.green.attaparunever2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class SystemPost extends UpdatedAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquiryId;

    @ManyToOne
    @JoinColumn(name = "post_code", nullable = false)
    private Code post;

    @ManyToOne
    @JoinColumn(name = "role_code", nullable = false)
    private Code role;

    @Column(length = 50, nullable = false)
    private String inquiryTitle;

    @Column(length = 500, nullable = false)
    private String inquiryDetail;

    @Column(length = 300)
    private String pic;

    @Column(nullable = false)
    private Long Id;
}
