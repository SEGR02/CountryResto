package ar.com.country.restaurant.web.dto.order;

import ar.com.country.restaurant.dao.entities.CardType;
import lombok.Data;

@Data
public class OrderPaymentMethodDTO {
    private CardType type;
    private String number;
}
