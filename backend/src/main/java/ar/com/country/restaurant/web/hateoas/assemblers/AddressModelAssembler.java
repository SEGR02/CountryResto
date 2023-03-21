package ar.com.country.restaurant.web.hateoas.assemblers;

import ar.com.country.restaurant.dao.entities.Address;
import ar.com.country.restaurant.dao.entities.User;
import ar.com.country.restaurant.web.controllers.AddressController;
import ar.com.country.restaurant.web.controllers.UserController;
import ar.com.country.restaurant.web.dto.AddressDTO;
import ar.com.country.restaurant.web.mappers.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
@RequiredArgsConstructor
public class AddressModelAssembler implements RepresentationModelAssembler<Address, AddressDTO> {
    private final AddressMapper addressMapper;
    private Address address;

    @Override
    public AddressDTO toModel(Address address) {
        this.address = address;
        AddressDTO model = addressMapper.toDto(address);
        model.add(selfLink(), userLink());
        return model;
    }

    private Link selfLink() {
        return linkTo(AddressController.class)
                .slash(address.getId())
                .withSelfRel();
    }

    private Link userLink() {
        User user = address.getUser();
        return linkTo(UserController.class)
                .slash(user.getId())
                .withRel("user");
    }

}
