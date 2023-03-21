package ar.com.country.restaurant.services.impl;

import ar.com.country.restaurant.dao.entities.PaymentMethod;
import ar.com.country.restaurant.dao.entities.User;
import ar.com.country.restaurant.exceptions.CardNumberAlreadyExistsException;
import ar.com.country.restaurant.exceptions.PaymentMethodNotFoundException;
import ar.com.country.restaurant.repositories.PaymentMethodRepository;
import ar.com.country.restaurant.services.PaymentMethodService;
import ar.com.country.restaurant.services.UserService;
import ar.com.country.restaurant.util.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {
    private final UserService userService;
    private final PaymentMethodRepository paymentMethodRepository;

    @Override
    public List<PaymentMethod> getPaymentMethodsOfUser(Long userId) {
        return paymentMethodRepository.findAllByUserId(userId);
    }

    @Override
    public PaymentMethod getPaymentMethodById(Long userId, Long paymentMethodId) {
        return paymentMethodRepository
                .findByIdAndUserId(paymentMethodId, userId)
                .orElseThrow(() -> new PaymentMethodNotFoundException(paymentMethodId));
    }

    @Override
    @Transactional
    public PaymentMethod addPaymentMethodToUser(Long userId, PaymentMethod providedPaymentMethod) {
        ensureUniqueCardNumber(providedPaymentMethod.getNumber());
        User user = userService.getUserById(userId);
        user.addPaymentMethod(providedPaymentMethod);
        return paymentMethodRepository.saveAndFlush(providedPaymentMethod);
    }

    @Override
    @Transactional
    public PaymentMethod updatePaymentMethod(Long userId, Long paymentMethodId, PaymentMethod updatedPaymentMethod) {
        PaymentMethod oldPaymentMethod = getPaymentMethodById(userId, paymentMethodId);
        if (!oldPaymentMethod.getNumber().equals(updatedPaymentMethod.getNumber())) {
            ensureUniqueCardNumber(updatedPaymentMethod.getNumber());
        }
        BeanUtils.copyProperties(updatedPaymentMethod, oldPaymentMethod);
        return paymentMethodRepository.save(oldPaymentMethod);
    }

    @Override
    public PaymentMethod deletePaymentMethod(Long userId, Long paymentMethodId) {
        PaymentMethod paymentMethodToDelete = getPaymentMethodById(userId, paymentMethodId);
        paymentMethodRepository.deleteById(paymentMethodId);
        return paymentMethodToDelete;
    }

    private void ensureUniqueCardNumber(String cardNumber) {
        boolean cardNumberExists = paymentMethodRepository.existsByCardNumber(cardNumber);
        if (cardNumberExists) {
            throw new CardNumberAlreadyExistsException();
        }
    }

}
