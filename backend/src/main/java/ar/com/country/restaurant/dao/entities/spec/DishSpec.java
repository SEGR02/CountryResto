package ar.com.country.restaurant.dao.entities.spec;

import ar.com.country.restaurant.dao.entities.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public final class DishSpec {
    private final Dish dish;
    private final Long categoryId;
    private final MultipartFile image;

    public DishSpec(Dish dish, Long categoryId) {
        this.dish = dish;
        this.categoryId = categoryId;
        this.image = null;
    }

    public boolean imageProvided() {
        return nonNull(image) && !image.isEmpty();
    }

    public Dish dish() {
        return dish;
    }

    public Long categoryId() {
        return categoryId;
    }

    public MultipartFile image() {
        return image;
    }

}
