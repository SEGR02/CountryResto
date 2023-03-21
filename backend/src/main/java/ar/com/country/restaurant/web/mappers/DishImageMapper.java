package ar.com.country.restaurant.web.mappers;

import ar.com.country.restaurant.dao.entities.DishImage;
import ar.com.country.restaurant.web.dto.DishImageDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DishImageMapper {

    DishImageDTO toDto(DishImage dishImage);

}
