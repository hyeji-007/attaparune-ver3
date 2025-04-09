package com.green.attaparunever2.entity;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class BlackListIds implements Serializable {
    private Long restaurantId;
    private Long userId;
}