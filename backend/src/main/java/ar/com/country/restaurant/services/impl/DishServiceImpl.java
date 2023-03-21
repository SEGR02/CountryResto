package ar.com.country.restaurant.services.impl;

import ar.com.country.restaurant.dao.entities.Dish;
import ar.com.country.restaurant.dao.entities.DishCategory;
import ar.com.country.restaurant.dao.entities.DishImage;
import ar.com.country.restaurant.dao.entities.Promotion;
import ar.com.country.restaurant.dao.entities.criteria.DishFilterCriteria;
import ar.com.country.restaurant.dao.entities.spec.DishSpec;
import ar.com.country.restaurant.exceptions.DishNotFoundException;
import ar.com.country.restaurant.repositories.DishRepository;
import ar.com.country.restaurant.services.DishCategoryService;
import ar.com.country.restaurant.services.DishImageUploaderService;
import ar.com.country.restaurant.services.DishService;
import ar.com.country.restaurant.util.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    private final DishCategoryService dishCategoryService;
    private final DishRepository dishRepository;
    private final DishImageUploaderService dishImageUploaderService;

    @Override
    public Page<Dish> searchDishByNameAndDescription(String query, Pageable pageable) {
        return dishRepository.searchDishByNameAndDescription(query, pageable);
    }

    @Override
    public Page<Dish> getDishes(DishFilterCriteria filterCriteria) {
        if (filterCriteria.filterByCategory()) {
            return dishRepository.findDishesByDishCategoryId(filterCriteria.categoryId(), filterCriteria.pageable());
        }
        return dishRepository.findAll(filterCriteria.pageable());
    }

    @Override
    public Dish getDishById(Long dishId) {
        return dishRepository
                .findById(dishId)
                .orElseThrow(() -> new DishNotFoundException(dishId));
    }

    @Override
    @Transactional
    public Dish createDish(DishSpec dishSpec) {
        Dish newDish = dishSpec.dish();
        DishCategory dishCategory = dishCategoryService.getDishCategoryById(dishSpec.categoryId());
        newDish.setCategory(dishCategory);
        if (newDish.hasPromotion()) {
            Promotion promotion = newDish.getPromotion();
            promotion.setDish(newDish);
        }
        if (dishSpec.imageProvided()) {
            DishImage image = dishImageUploaderService.uploadImage(dishSpec.image());
            newDish.setImage(image);
        }
        return dishRepository.saveAndFlush(newDish);
    }

    @Override
    @Transactional
    public Dish updateDish(Long dishId, DishSpec dishSpec) {
        Dish dishToUpdate = getDishById(dishId);
        Dish updatedDish = dishSpec.dish();
        DishCategory updatedDishCategory = dishCategoryService.getDishCategoryById(dishSpec.categoryId());
        dishToUpdate.setCategory(updatedDishCategory);
        if (updatedDish.hasPromotion()) {
            Promotion promotion = updatedDish.getPromotion();
            dishToUpdate.setPromotion(promotion);
        }  else {
            dishToUpdate.removePromotion();
        }
        if (dishSpec.imageProvided()) {
            DishImage image = dishImageUploaderService.uploadOrUpdateImage(dishToUpdate, dishSpec.image());
            dishToUpdate.setImage(image);
        }
        BeanUtils.copyProperties(updatedDish, dishToUpdate);
        return dishRepository.saveAndFlush(dishToUpdate);
    }

    @Override
    public Dish deleteDishById(Long dishId) {
        Dish dishToDelete = getDishById(dishId);
        dishRepository.deleteById(dishId);
        return dishToDelete;
    }

}
