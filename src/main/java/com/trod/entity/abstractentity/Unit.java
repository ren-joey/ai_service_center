package com.trod.entity.abstractentity;

import com.trod.constant.RarityEnum;
import com.trod.constant.ThemeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class Unit extends UUIDEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RarityEnum rarity;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private ThemeEnum theme;
}
