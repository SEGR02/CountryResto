package ar.com.country.restaurant.dao.entities.spec;

import ar.com.country.restaurant.dao.entities.DishCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public final class DishCategorySpec {
    private final DishCategory dishCategory;
    private final MultipartFile image;

    public DishCategorySpec(DishCategory dishCategory) {
        this.dishCategory = dishCategory;
        this.image = null;
    }

    public boolean imageProvided() {
        return nonNull(image) && !image.isEmpty();
    }

    public DishCategory dishCategory() {
        return dishCategory;
    }

    public MultipartFile image() {
        return image;
    }

}
