package ar.com.country.restaurant.exceptions;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long id) {
        super(String.format("Order with id %d not found", id));
    }

}
