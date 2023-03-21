package ar.com.country.restaurant.dao.entities;

import lombok.*;

import javax.persistence.*;

import static java.util.Objects.nonNull;

@Entity
@Table(name = "promotions")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_id")
    private Long id;

    @Column(name = "discount_percentage")
    private Double discountPercentage;

    @OneToOne(fetch = FetchType.LAZY)
    private Dish dish;

    public boolean isValidPromotion() {
        return nonNull(discountPercentage) && discountPercentage > 0 && discountPercentage <= 100;
    }

    public double getPriceWithPromotion() {
        double dishPrice = dish.getPrice();
        return dishPrice - (dishPrice * discountPercentage / 100);
    }

}
