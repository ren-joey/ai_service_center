package com.trod.constant;

import lombok.Getter;

@Getter
public enum RarityEnum {
    NORMAL(0),
    RARE(1),
    SUPER_RARE(2),
    EPIC(3),
    LEGENDARY(4);

    private final int index;

    RarityEnum(int index) {
        this.index = index;
    }

    public static RarityEnum fromIndex(int index) {
        for (RarityEnum role : RarityEnum.values()) {
            if (role.getIndex() == index) {
                return role;
            }
        }
        return null;
    }

    public RarityAbbreviationEnum toAbbreviation() {
        return RarityAbbreviationEnum.fromIndex(index);
    }
}
