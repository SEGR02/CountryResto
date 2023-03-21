package ar.com.country.restaurant.dao.entities;


import ar.com.country.restaurant.exceptions.ItemNotFoundException;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Entity
@Table(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(
            mappedBy = "cart",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<CartItem> items;

    public void addCartItem(CartItem cartItem) {
        if (isNull(items)) {
            items = new ArrayList<>();
        }
        items.add(cartItem);
        cartItem.setCart(this);
    }

    public CartItem getCartItemById(Long itemId) {
        return items.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException(itemId));
    }

    public void removeCartItem(Long itemId) {
        CartItem cartItem = getCartItemById(itemId);
        cartItem.setCart(null);
        items.remove(cartItem);
    }

    public double getSubTotal() {
        return items.stream()
                .mapToDouble(CartItem::getSubTotal)
                .sum();
    }

    public void emptyCart() {
        items.forEach(item -> item.setCart(null));
        items.clear();
    }

    public List<CartItem> getItems() {
        if (isNull(items)) {
            items = new ArrayList<>();
        }
        return items;
    }

}
