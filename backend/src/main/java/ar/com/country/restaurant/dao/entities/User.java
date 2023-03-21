package ar.com.country.restaurant.dao.entities;

import ar.com.country.restaurant.dao.entities.order.Order;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @Column
    private String password;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Order> orders;

    @OneToOne(fetch = FetchType.LAZY)
    private Cart cart;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Address> addresses;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<PaymentMethod> paymentMethods;

    public void addAddress(Address address) {
        if (isNull(addresses)) {
            addresses = new ArrayList<>();
        }
        addresses.add(address);
        address.setUser(this);
    }

    public void addPaymentMethod(PaymentMethod paymentMethod) {
        if (isNull(paymentMethods)) {
            paymentMethods = new ArrayList<>();
        }
        paymentMethods.add(paymentMethod);
        paymentMethod.setUser(this);
    }

    public Cart createAndGetCart() {
        if (isNull(cart)) {
            cart = Cart.builder().user(this).build();
        }
        return cart;
    }

}
