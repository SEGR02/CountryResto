package ar.com.country.restaurant.dao.entities.criteria;

import org.springframework.data.domain.Pageable;

import static java.util.Objects.nonNull;

public record DishFilterCriteria(
        Long categoryId,
        Pageable pageable
) {

    public boolean filterByCategory() {
        return nonNull(categoryId);
    }

}
