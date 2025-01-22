package com.trod.entity;

import com.trod.entity.abstractentity.IdentityEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Wallet extends IdentityEntity {
    @Column(columnDefinition = "integer default 0")
    private int gifted_points = 0;

    @Column(columnDefinition = "integer default 0")
    private int paid_points = 0;

    @Column(columnDefinition = "integer default 0")
    private int in_game_points = 0;

    public int totalPoints () {
        return gifted_points + paid_points + in_game_points;
    }

    public int totalValuedPoints () {
        return paid_points + gifted_points;
    }
}
