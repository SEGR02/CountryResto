package ar.com.country.restaurant.dao.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "deliveries")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @OneToOne
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DeliveryStatus status;
}