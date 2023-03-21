package ar.com.country.restaurant.exceptions;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(Long itemId) {
        super(String.format("Item with id %d not found", itemId));
    }

}
