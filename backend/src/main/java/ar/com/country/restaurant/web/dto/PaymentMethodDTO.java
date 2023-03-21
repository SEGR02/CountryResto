package ar.com.country.restaurant.web.dto;

import ar.com.country.restaurant.dao.entities.CardType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Relation(itemRelation = "payment_method", collectionRelation = "payment_methods")
public final class PaymentMethodDTO extends RepresentationModel<PaymentMethodDTO> {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final Long id;

    @NotNull
    private final CardType type;

    @NotBlank
    private final String number;

    @NotBlank
    private final String holder;

    @NotNull
    private final LocalDate expirationDate;

    @NotBlank
    private final String cvv;
}
