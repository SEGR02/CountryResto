package ar.com.country.restaurant.dao.entities.order;


import ar.com.country.restaurant.dao.entities.CardType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_payment_methods")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderPaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_payment_method_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private CardType type;

    @Column
    private String number;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderPaymentMethod that = (OrderPaymentMethod) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
