package ar.com.country.restaurant.web.hateoas.assemblers;

import ar.com.country.restaurant.dao.entities.Cart;
import ar.com.country.restaurant.dao.entities.User;
import ar.com.country.restaurant.web.controllers.CartController;
import ar.com.country.restaurant.web.controllers.UserController;
import ar.com.country.restaurant.web.dto.CartDTO;
import ar.com.country.restaurant.web.mappers.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
@RequiredArgsConstructor
public class CartModelAssembler implements RepresentationModelAssembler<Cart, CartDTO> {
    private final CartMapper cartMapper;
    private Cart cart;

    @Override
    @NonNull
    public CartDTO toModel(@NonNull Cart cart) {
        this.cart = cart;
        CartDTO model = cartMapper.toDto(cart);
        model.add(selfLink(), userLink());
        return model;
    }

    public Link selfLink() {
        return linkTo(CartController.class).withSelfRel();
    }

    public Link userLink() {
        User user = cart.getUser();
        return linkTo(UserController.class)
                .slash(user.getId())
                .withRel("user");
    }

}
