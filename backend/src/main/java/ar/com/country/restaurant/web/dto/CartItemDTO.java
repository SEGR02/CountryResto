package ar.com.country.restaurant.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class CartItemDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private DishResponseDTO dish;

    @NotNull
    @PositiveOrZero
    private Integer quantity;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double subTotal;
}
