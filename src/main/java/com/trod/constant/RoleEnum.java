package com.trod.constant;

import lombok.Getter;

@Getter
public enum RoleEnum {
    PLAYER(0),
    GAME_MASTER(1),
    ADMIN(2);

    private final int index;

    RoleEnum(int index) {
        this.index = index;
    }

    public static RoleEnum fromIndex(int index) {
        for (RoleEnum role : RoleEnum.values()) {
            if (role.index == index) {
                return role;
            }
        }
        return null;
    }
}
