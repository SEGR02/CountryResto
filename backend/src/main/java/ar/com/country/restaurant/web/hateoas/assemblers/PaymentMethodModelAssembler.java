package ar.com.country.restaurant.web.hateoas.assemblers;

import ar.com.country.restaurant.dao.entities.PaymentMethod;
import ar.com.country.restaurant.dao.entities.User;
import ar.com.country.restaurant.web.controllers.PaymentMethodController;
import ar.com.country.restaurant.web.controllers.UserController;
import ar.com.country.restaurant.web.dto.PaymentMethodDTO;
import ar.com.country.restaurant.web.mappers.PaymentMethodMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
@RequiredArgsConstructor
public class PaymentMethodModelAssembler implements RepresentationModelAssembler<PaymentMethod, PaymentMethodDTO> {
    private final PaymentMethodMapper paymentMethodMapper;
    private PaymentMethod paymentMethod;

    @Override
    public PaymentMethodDTO toModel(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        PaymentMethodDTO model = paymentMethodMapper.toDto(paymentMethod);
        model.add(selfLink(), userLink());
        return model;
    }

    private Link selfLink() {
        return linkTo(PaymentMethodController.class)
                .slash(paymentMethod.getId())
                .withSelfRel();
    }

    private Link userLink() {
        User user = paymentMethod.getUser();
        return linkTo(UserController.class)
                .slash(user.getId())
                .withRel("user");
    }

}
