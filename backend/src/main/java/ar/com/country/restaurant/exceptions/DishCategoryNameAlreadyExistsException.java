package ar.com.country.restaurant.exceptions;

public class DishCategoryNameAlreadyExistsException extends RuntimeException {
    public DishCategoryNameAlreadyExistsException(String name) {
        super("Dish category name already exists: " + name);
    }
}
