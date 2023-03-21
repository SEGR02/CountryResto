package ar.com.country.restaurant.exceptions;

public class EmailAlreadyTakenException extends RuntimeException {

    public EmailAlreadyTakenException(String email) {
        super(String.format("The email: %s is already taken", email));
    }
    
}
