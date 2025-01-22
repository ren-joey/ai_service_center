package com.trod.constant;

import lombok.Getter;

@Getter
public enum PropertyTypeEnum {
    ITEM(0),
    MONSTER(1),
    MAIN_CHARACTER(2);

    private final int index;

    PropertyTypeEnum(int index) {
        this.index = index;
    }

    public static PropertyTypeEnum fromIndex(int index) {
        for (PropertyTypeEnum propertyTypeEnum : PropertyTypeEnum.values()) {
            if (propertyTypeEnum.index == index) {
                return propertyTypeEnum;
            }
        }
        return null;
    }
}
