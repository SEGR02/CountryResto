package ar.com.country.restaurant.services;

import ar.com.country.restaurant.dao.entities.PaymentMethod;

import java.util.List;

public interface PaymentMethodService {

    List<PaymentMethod> getPaymentMethodsOfUser(Long userId);

    PaymentMethod getPaymentMethodById(Long userId, Long paymentMethodId);

    PaymentMethod addPaymentMethodToUser(Long userId, PaymentMethod providedPaymentMethod);

    PaymentMethod updatePaymentMethod(Long userId, Long paymentMethodId, PaymentMethod updatedPaymentMethod);

    PaymentMethod deletePaymentMethod(Long userId, Long paymentMethodId);
    
}
