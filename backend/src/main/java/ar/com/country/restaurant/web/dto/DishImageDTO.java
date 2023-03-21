package ar.com.country.restaurant.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DishImageDTO(
        String publicId,
        String url
) {
}
