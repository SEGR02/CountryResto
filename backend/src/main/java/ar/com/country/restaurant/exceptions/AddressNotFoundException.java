package ar.com.country.restaurant.exceptions;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException(Long id) {
        super(String.format("Address with id: %s not found", id));
    }

}
