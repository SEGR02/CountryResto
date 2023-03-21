package ar.com.country.restaurant.web.mappers;

import ar.com.country.restaurant.dao.entities.Address;
import ar.com.country.restaurant.web.dto.AddressDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toEntity(AddressDTO addressDto);

    AddressDTO toDto(Address address);

    List<AddressDTO> toDtoList(List<Address> addresses);

}
