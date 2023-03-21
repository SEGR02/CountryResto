package ar.com.country.restaurant.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@Relation(itemRelation = "dishCategory", collectionRelation = "dishCategories")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public final class DishCategoryDTO extends RepresentationModel<DishCategoryDTO> {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank
    private String name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private DishImageDTO image;
}
