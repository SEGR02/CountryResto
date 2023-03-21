package ar.com.country.restaurant.web.mappers;

import ar.com.country.restaurant.dao.entities.DishCategory;
import ar.com.country.restaurant.web.dto.DishCategoryDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring",
        uses = {DishMapper.class, DishImageMapper.class}
)
public abstract class DishCategoryMapper {
    @Autowired
    private DishImageMapper dishImageMapper;

    public abstract DishCategory toEntity(DishCategoryDTO dishCategoryDto);

    public abstract DishCategoryDTO toDto(DishCategory dishCategory);

    @AfterMapping
    public void setDishImage(DishCategory dishCategory, @MappingTarget DishCategoryDTO dishCategoryDto) {
        dishCategoryDto.setImage(dishImageMapper.toDto(dishCategory.getImage()));
    }

}
