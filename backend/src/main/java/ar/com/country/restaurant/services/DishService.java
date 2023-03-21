package ar.com.country.restaurant.services;

import ar.com.country.restaurant.dao.entities.Dish;
import ar.com.country.restaurant.dao.entities.criteria.DishFilterCriteria;
import ar.com.country.restaurant.dao.entities.spec.DishSpec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DishService {

    Page<Dish> searchDishByNameAndDescription(String query, Pageable pageable);

    Page<Dish> getDishes(DishFilterCriteria filterCriteria);

    Dish getDishById(Long id);

    Dish createDish(DishSpec dishSpec);

    Dish updateDish(Long dishId, DishSpec dishSpec);

    Dish deleteDishById(Long dishId);

}
