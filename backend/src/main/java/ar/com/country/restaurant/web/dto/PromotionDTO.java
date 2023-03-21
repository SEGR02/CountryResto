package ar.com.country.restaurant.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.PositiveOrZero;

@Data
public class PromotionDTO {
    @PositiveOrZero
    @DecimalMax("100.00")
    private Double discountPercentage;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double priceWithPromotion;
}
