package ar.com.country.restaurant.dao.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "payment_methods")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_method_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private CardType type;

    @Column(unique = true)
    private String number;

    @Column
    private String holder;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column
    private String cvv;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
}
