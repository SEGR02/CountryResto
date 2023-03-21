package ar.com.country.restaurant.exceptions;

public class ApplicationException extends RuntimeException {

    public ApplicationException(final Throwable e) {
        super(e);
    }
    
}
