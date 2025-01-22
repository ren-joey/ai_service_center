package com.trod.constant;

public enum ThemeEnum {
    VANILLA(0);

    private final int index;

    ThemeEnum(int index) {
        this.index = index;
    }

    public static ThemeEnum fromIndex(int index) {
        for (ThemeEnum theme : ThemeEnum.values()) {
            if (theme.index == index) {
                return theme;
            }
        }
        return null;
    }
}
