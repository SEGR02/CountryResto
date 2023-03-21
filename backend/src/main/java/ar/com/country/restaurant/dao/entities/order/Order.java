package ar.com.country.restaurant.dao.entities.order;

import ar.com.country.restaurant.dao.entities.PaymentMethod;
import ar.com.country.restaurant.dao.entities.User;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column
    private LocalDateTime statusUpdatedAt;

    @Column
    private Double total;

    @Column
    private String reference;

    @Column
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdAt;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private OrderPaymentMethod orderPaymentMethod;

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        orderPaymentMethod = OrderPaymentMethod.builder()
                .number(paymentMethod.getNumber())
                .type(paymentMethod.getType())
                .order(this)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return getId() != null && Objects.equals(getId(), order.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
