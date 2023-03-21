package ar.com.country.restaurant.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Relation(itemRelation = "cart")
public class CartDTO extends RepresentationModel<CartDTO> {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private List<CartItemDTO> items;
    private Double subTotal;
}
