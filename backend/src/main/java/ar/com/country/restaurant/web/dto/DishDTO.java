package ar.com.country.restaurant.web.dto;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public record DishDTO(
        @NotBlank
        String name,

        @NotBlank
        String description,

        @PositiveOrZero
        Double price,

        @Positive
        Integer portionPerUnit,

        @Positive
        Integer people,

        @NotNull
        Long categoryId,

        @Nullable
        PromotionDTO promotion
) {
}
