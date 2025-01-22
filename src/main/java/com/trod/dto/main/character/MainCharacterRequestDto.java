package com.trod.dto.main.character;

import com.trod.constant.RarityEnum;

public record MainCharacterRequestDto (
        String name,
        String description,
        RarityEnum rarity,
        Integer cost,
        Integer health,
        Integer mana
) {
}
