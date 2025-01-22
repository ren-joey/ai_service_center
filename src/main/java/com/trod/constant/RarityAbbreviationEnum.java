package com.trod.constant;

public enum RarityAbbreviationEnum {
    N(0),
    R(1),
    SR(2),
    SSR(3),
    UR(4);

    private final int index;

    RarityAbbreviationEnum(int index) {
        this.index = index;
    }

    public static RarityAbbreviationEnum fromIndex(int index) {
        for (RarityAbbreviationEnum rarity : RarityAbbreviationEnum.values()) {
            if (rarity.index == index) {
                return rarity;
            }
        }
        return null;
    }

    public RarityEnum toFullForm() {
        return RarityEnum.fromIndex(index);
    }
}
