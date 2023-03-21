package ar.com.country.restaurant.exceptions;

public class DishNotFoundException extends RuntimeException {

    public DishNotFoundException(Long dishId) {
        super(String.format("Dish with id: %s not found", dishId));
    }

}
