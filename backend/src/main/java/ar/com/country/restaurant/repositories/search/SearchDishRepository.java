package ar.com.country.restaurant.repositories.search;

import ar.com.country.restaurant.dao.entities.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchDishRepository {

    Page<Dish> searchDishByNameAndDescription(String query, Pageable pageable);

}
