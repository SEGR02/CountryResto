package ar.com.country.restaurant.web.mappers;

import ar.com.country.restaurant.dao.entities.order.Order;
import ar.com.country.restaurant.web.dto.order.OrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO toDto(Order order);

}
