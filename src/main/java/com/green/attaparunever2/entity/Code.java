package com.green.attaparunever2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Code {
    @Id
    @Column(length = 5)
    private String code;

    @Column(length = 3)
    private String codeId;
    @Column(length = 20, nullable = false)
    private String name;
}