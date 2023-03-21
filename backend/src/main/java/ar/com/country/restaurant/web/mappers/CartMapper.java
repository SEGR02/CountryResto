package ar.com.country.restaurant.web.mappers;

import ar.com.country.restaurant.dao.entities.Cart;
import ar.com.country.restaurant.web.dto.CartDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CartItemMapper.class)
public interface CartMapper {

    Cart toEntity(CartDTO cartDto);

    CartDTO toDto(Cart cart);

}
