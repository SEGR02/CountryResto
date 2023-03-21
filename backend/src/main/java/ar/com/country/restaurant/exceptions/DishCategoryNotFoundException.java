package ar.com.country.restaurant.exceptions;

public class DishCategoryNotFoundException extends RuntimeException {

    public DishCategoryNotFoundException() {
        super("Dish Category not found");
    }

    public DishCategoryNotFoundException(Long categoryId) {
        super(String.format("Dish category with id: %s not found", categoryId));
    }

}
