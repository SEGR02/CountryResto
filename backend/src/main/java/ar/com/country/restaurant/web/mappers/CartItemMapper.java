package ar.com.country.restaurant.web.mappers;

import ar.com.country.restaurant.dao.entities.CartItem;
import ar.com.country.restaurant.web.dto.CartItemDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = DishMapper.class)
public interface CartItemMapper {

    CartItem toEntity(CartItemDTO cartItemDTO);

    CartItemDTO toDto(CartItem cartItem);

    List<CartItemDTO> toDtoList(List<CartItem> cartItems);

}
