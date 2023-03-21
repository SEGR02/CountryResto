package ar.com.country.restaurant.services;

import ar.com.country.restaurant.dao.entities.DishCategory;
import ar.com.country.restaurant.dao.entities.spec.DishCategorySpec;

import java.util.List;

public interface DishCategoryService {

    List<DishCategory> getDishCategories();

    DishCategory getDishCategoryById(Long categoryId);

    DishCategory createDishCategory(DishCategorySpec dishCategorySpec);

    DishCategory updateDishCategory(Long categoryId, DishCategorySpec dishCategorySpec);

    DishCategory deleteDishCategory(Long id);

}
