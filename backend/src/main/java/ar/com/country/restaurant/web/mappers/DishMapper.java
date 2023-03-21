package ar.com.country.restaurant.web.mappers;

import ar.com.country.restaurant.dao.entities.Dish;
import ar.com.country.restaurant.web.dto.DishDTO;
import ar.com.country.restaurant.web.dto.DishResponseDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring",
        uses = {DishCategoryMapper.class, DishImageMapper.class}
)
public abstract class DishMapper {
    @Autowired
    private DishImageMapper dishImageMapper;

    public abstract Dish toEntity(DishDTO dishDto);

    @Mapping(target = "hasPromotion", expression = "java(dish.hasPromotion())")
    public abstract DishResponseDTO toResponseDto(Dish dish);

    @AfterMapping
    public void setDishImage(Dish dish, @MappingTarget DishResponseDTO dishDto) {
        dishDto.setImage(dishImageMapper.toDto(dish.getImage()));
    }

}
