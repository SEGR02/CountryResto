package ar.com.country.restaurant.web.hateoas.assemblers;

import ar.com.country.restaurant.dao.entities.order.Order;
import ar.com.country.restaurant.dao.entities.order.OrderStatus;
import ar.com.country.restaurant.web.controllers.OrderController;
import ar.com.country.restaurant.web.dto.order.OrderDTO;
import ar.com.country.restaurant.web.mappers.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
@RequiredArgsConstructor
public class OrderModelAssembler implements RepresentationModelAssembler<Order, OrderDTO> {
    private final OrderMapper orderMapper;
    private Order order;

    @Override
    @NonNull
    public OrderDTO toModel(@NonNull Order order) {
        this.order = order;
        OrderDTO model = orderMapper.toDto(order);
        model.add(selfLink());

        Optional<Link> cancelLink = cancelLink();
        cancelLink.ifPresent(model::add);

        return model;
    }

    private Link selfLink() {
        return linkTo(OrderController.class)
                .slash(order.getId())
                .withSelfRel();
    }

    private Optional<Link> cancelLink() {
        if (List.of(OrderStatus.DELIVERED, OrderStatus.CANCELLED).contains(order.getStatus())) {
            return Optional.empty();
        }
        return Optional.of(
                linkTo(OrderController.class)
                        .slash(order.getId())
                        .slash("cancel")
                        .withRel("cancel")
        );
    }

}
