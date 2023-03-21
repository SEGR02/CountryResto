package ar.com.country.restaurant.exceptions;

public class PaymentMethodNotFoundException extends RuntimeException {

    public PaymentMethodNotFoundException(Long id) {
        super(String.format("Payment method with id: %s not found", id));
    }
    
}
