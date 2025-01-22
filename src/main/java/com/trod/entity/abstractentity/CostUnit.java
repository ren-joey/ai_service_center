package com.trod.entity.abstractentity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class CostUnit extends Unit {
    @Column(nullable = false)
    private Integer cost;
}
