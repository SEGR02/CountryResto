package ar.com.country.restaurant.web.mappers;

import ar.com.country.restaurant.dao.entities.PaymentMethod;
import ar.com.country.restaurant.web.dto.PaymentMethodDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {

    PaymentMethod toEntity(PaymentMethodDTO paymentMethodDTO);

    PaymentMethodDTO toDto(PaymentMethod paymentMethod);

    List<PaymentMethodDTO> toDtoList(List<PaymentMethod> paymentMethods);
    
}
