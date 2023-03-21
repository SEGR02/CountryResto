package ar.com.country.restaurant.dao.entities;

import ar.com.country.restaurant.dao.entities.order.Order;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "receipts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_at")
    private Date created;

    @Column
    private double total;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    private Order order;

    @OneToOne(fetch = FetchType.LAZY)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReceiptStatus status;
}
