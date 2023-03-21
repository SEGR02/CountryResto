package ar.com.country.restaurant.web.hateoas.assemblers;

import ar.com.country.restaurant.dao.entities.Dish;
import ar.com.country.restaurant.dao.entities.DishCategory;
import ar.com.country.restaurant.web.controllers.DishCategoryController;
import ar.com.country.restaurant.web.controllers.DishController;
import ar.com.country.restaurant.web.dto.DishResponseDTO;
import ar.com.country.restaurant.web.mappers.DishMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
@RequiredArgsConstructor
public class DishModelAssembler implements RepresentationModelAssembler<Dish, DishResponseDTO> {
    private final DishMapper dishMapper;
    private Dish dish;

    @Override
    public DishResponseDTO toModel(Dish dish) {
        this.dish = dish;
        DishResponseDTO model = dishMapper.toResponseDto(dish);
        model.add(selfLink(), categoryLink());
        return model;
    }

    private Link selfLink() {
        return linkTo(DishController.class)
                .slash(dish.getId())
                .withSelfRel();
    }

    private Link categoryLink() {
        DishCategory category = dish.getCategory();
        return linkTo(DishCategoryController.class)
                .slash(category.getId())
                .withRel("category");
    }

}
