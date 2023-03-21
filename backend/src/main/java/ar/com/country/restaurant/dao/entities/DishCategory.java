package ar.com.country.restaurant.dao.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import static java.util.Objects.nonNull;

@Entity
@Table(name = "dish_categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishCategory implements WithImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column
    private String name;

    @Embedded
    private DishImage image;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Dish> dishes;

    @Override
    public boolean hasImage() {
        return nonNull(image) && image.isValidImage();
    }

}
