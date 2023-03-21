package ar.com.country.restaurant.exceptions;

public class CardNumberAlreadyExistsException extends RuntimeException {

    public CardNumberAlreadyExistsException() {
        super("Card number already exists");
    }

}
