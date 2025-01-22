package com.trod.entity;

import com.trod.constant.PropertyTypeEnum;
import com.trod.entity.abstractentity.IdentityEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Property extends IdentityEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PropertyTypeEnum propertyTypeEnum;

    @Column(nullable = false)
    private String itemId;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column()
    private LocalDateTime expiresAt;
}
