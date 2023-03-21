package ar.com.country.restaurant.exceptions;

public class CartEmptyException extends RuntimeException {
    public CartEmptyException(Long id) {
        super("Cart of user with id " + id + " is empty");
    }
}
