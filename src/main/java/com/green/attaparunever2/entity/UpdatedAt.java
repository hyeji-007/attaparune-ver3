package com.green.attaparunever2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass //Entity 부모역할
@EntityListeners(AuditingEntityListener.class)
public class UpdatedAt extends CreatedAt {
    @LastModifiedDate //insert, update 되었을 때 현재일시값을 넣는다.
    private LocalDateTime updatedAt;
}
