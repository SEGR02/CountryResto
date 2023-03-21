package ar.com.country.restaurant.services.impl;

import ar.com.country.restaurant.dao.entities.DishCategory;
import ar.com.country.restaurant.dao.entities.DishImage;
import ar.com.country.restaurant.dao.entities.spec.DishCategorySpec;
import ar.com.country.restaurant.exceptions.DishCategoryNameAlreadyExistsException;
import ar.com.country.restaurant.exceptions.DishCategoryNotFoundException;
import ar.com.country.restaurant.repositories.DishCategoryRepository;
import ar.com.country.restaurant.services.DishCategoryService;
import ar.com.country.restaurant.services.DishImageUploaderService;
import ar.com.country.restaurant.util.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishCategoryServiceImpl implements DishCategoryService {
    private final DishImageUploaderService dishImageUploaderService;
    private final DishCategoryRepository dishCategoryRepository;

    @Override
    public List<DishCategory> getDishCategories() {
        return dishCategoryRepository.findAllDishCategoriesByNameAsc();
    }

    @Override
    public DishCategory getDishCategoryById(Long categoryId) {
        return dishCategoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new DishCategoryNotFoundException(categoryId));
    }

    @Override
    @Transactional
    public DishCategory createDishCategory(DishCategorySpec dishCategorySpec) {
        DishCategory newDishCategory = dishCategorySpec.dishCategory();
        ensureUniqueDishCategoryName(newDishCategory.getName());
        if (dishCategorySpec.imageProvided()) {
            DishImage image = dishImageUploaderService.uploadImage(dishCategorySpec.image());
            newDishCategory.setImage(image);
        }
        return dishCategoryRepository.save(newDishCategory);
    }

    @Override
    @Transactional
    public DishCategory updateDishCategory(Long categoryId, DishCategorySpec dishCategorySpec) {
        DishCategory categoryToUpdate = getDishCategoryById(categoryId);
        DishCategory updatedDishCategory = dishCategorySpec.dishCategory();
        if (!categoryToUpdate.getName().equals(updatedDishCategory.getName())) {
            ensureUniqueDishCategoryName(updatedDishCategory.getName());
        }
        BeanUtils.copyProperties(updatedDishCategory, categoryToUpdate);
        if (dishCategorySpec.imageProvided()) {
            DishImage image = dishImageUploaderService.uploadOrUpdateImage(categoryToUpdate, dishCategorySpec.image());
            categoryToUpdate.setImage(image);
        }
        return categoryToUpdate;
    }

    @Override
    public DishCategory deleteDishCategory(Long categoryId) {
        DishCategory categoryToDelete = getDishCategoryById(categoryId);
        dishCategoryRepository.deleteById(categoryId);
        return categoryToDelete;
    }

    private void ensureUniqueDishCategoryName(String name) {
        boolean nameAlreadyExists = dishCategoryRepository.existsByNameIgnoreCase(name);
        if (nameAlreadyExists) {
            throw new DishCategoryNameAlreadyExistsException(name);
        }
    }

}
