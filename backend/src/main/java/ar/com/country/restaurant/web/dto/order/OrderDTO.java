package ar.com.country.restaurant.web.dto.order;

import ar.com.country.restaurant.dao.entities.order.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;
import java.util.Calendar;

@Data
@EqualsAndHashCode(callSuper = true)
@Relation(itemRelation = "order", collectionRelation = "orders")
public class OrderDTO extends RepresentationModel<OrderDTO> {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private OrderStatus status;
    private LocalDateTime statusUpdatedAt;
    private Double total;
    private String reference;
    private Calendar createdAt;
    private OrderPaymentMethodDTO orderPaymentMethod;
}
