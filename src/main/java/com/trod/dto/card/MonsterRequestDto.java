package com.trod.dto.card;

import com.trod.constant.RarityEnum;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MonsterRequestDto (
        @NotBlank(message = "Name is required")
        @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
        String name,

        // TODO: 新增一個忘記是什麼的 column

        @NotBlank(message = "Description is required")
        @Size(min = 3, max = 1000, message = "Description must be between 3 and 100 characters")
        String description,

        @NotBlank(message = "Rarity is required")
        @DecimalMin(value = "0", message = "Rarity must be more or equal than 0")
        @DecimalMax(value = "4", message = "Rarity must be less or equal than 4")
        RarityEnum rarityEnum,

        @NotBlank(message = "Cost is required")
        @DecimalMin(value = "0", message = "Cost must be more or equal than 0")
        Integer cost,

        @NotBlank(message = "Health is required")
        @DecimalMin(value = "0", message = "Health must be more or equal than 0")
        Integer health,

        @NotBlank(message = "Mana is required")
        @DecimalMin(value = "0", message = "Mana must be more or equal than 0")
        Integer mana,

        @NotBlank(message = "Attack is required")
        @DecimalMin(value = "0", message = "Attack must be more or equal than 0")
        Integer attack,

        @NotBlank(message = "Defense is required")
        @DecimalMin(value = "0", message = "Defense must be more or equal than 0")
        Integer defense
) {
}
