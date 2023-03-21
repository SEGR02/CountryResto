package ar.com.country.restaurant.web.hateoas.assemblers;

import ar.com.country.restaurant.dao.entities.DishCategory;
import ar.com.country.restaurant.web.controllers.DishCategoryController;
import ar.com.country.restaurant.web.controllers.DishController;
import ar.com.country.restaurant.web.dto.DishCategoryDTO;
import ar.com.country.restaurant.web.mappers.DishCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class DishCategoryModelAssembler implements RepresentationModelAssembler<DishCategory, DishCategoryDTO> {
    private final DishCategoryMapper dishCategoryMapper;
    private DishCategory dishCategory;

    @Override
    @NonNull
    public DishCategoryDTO toModel(@NonNull DishCategory dishCategory) {
        this.dishCategory = dishCategory;
        DishCategoryDTO dishCategoryDTO = dishCategoryMapper.toDto(dishCategory);
        dishCategoryDTO.add(selfLink(), dishesLink());
        return dishCategoryDTO;
    }

    private Link selfLink() {
        return linkTo(DishCategoryController.class)
                .slash(dishCategory.getId())
                .withSelfRel();
    }

    private Link dishesLink() {
        return linkTo(methodOn(DishController.class)
                .getDishes(dishCategory.getId(), null)
        ).withRel("dishes");
    }

}
