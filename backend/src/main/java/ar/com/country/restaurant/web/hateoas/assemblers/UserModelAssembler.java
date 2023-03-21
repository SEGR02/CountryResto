package ar.com.country.restaurant.web.hateoas.assemblers;

import ar.com.country.restaurant.dao.entities.User;
import ar.com.country.restaurant.web.controllers.AddressController;
import ar.com.country.restaurant.web.controllers.PaymentMethodController;
import ar.com.country.restaurant.web.controllers.UserController;
import ar.com.country.restaurant.web.dto.UserDTO;
import ar.com.country.restaurant.web.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
@RequiredArgsConstructor
public class UserModelAssembler implements RepresentationModelAssembler<User, UserDTO> {
    private final UserMapper userMapper;
    private User user;

    @Override
    public UserDTO toModel(User user) {
        this.user = user;
        UserDTO model = userMapper.toDto(user);
        model.add(selfLink(), addressesLink(), paymentMethodsLink());
        return model;
    }

    private Link selfLink() {
        return linkTo(UserController.class)
                .slash(user.getId())
                .withSelfRel();
    }

    private Link addressesLink() {
        return linkTo(AddressController.class).withRel("addresses");
    }

    private Link paymentMethodsLink() {
        return linkTo(PaymentMethodController.class).withRel("payment_methods");
    }

}
