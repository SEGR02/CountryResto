package ar.com.country.restaurant.services.impl;

import ar.com.country.restaurant.dao.entities.Cart;
import ar.com.country.restaurant.dao.entities.PaymentMethod;
import ar.com.country.restaurant.dao.entities.User;
import ar.com.country.restaurant.dao.entities.order.Order;
import ar.com.country.restaurant.dao.entities.order.OrderStatus;
import ar.com.country.restaurant.exceptions.OrderNotFoundException;
import ar.com.country.restaurant.repositories.CartItemRepository;
import ar.com.country.restaurant.repositories.CartRepository;
import ar.com.country.restaurant.repositories.OrderRepository;
import ar.com.country.restaurant.services.OrderService;
import ar.com.country.restaurant.services.PaymentMethodService;
import ar.com.country.restaurant.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final UserService userService;
    private final PaymentMethodService paymentMethodService;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final Clock applicationClock;
    private final CartItemRepository cartItemRepository;

    @Override
    public List<Order> getOrdersOfUser(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Override
    public Order getOrderById(Long userId, Long orderId) {
        return orderRepository
                .findByIdAndUserId(orderId, userId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    @Override
    @Transactional
    public Order createOrder(Long userId, Long paymentMethodId) {
        User user = userService.getUserById(userId);
        PaymentMethod paymentMethod = paymentMethodService.getPaymentMethodById(userId, paymentMethodId);
        Cart cart = user.getCart();

        Order order = new Order();
        order.setUser(user);
        order.setPaymentMethod(paymentMethod);
        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setTotal(cart.getSubTotal());
        order.setReference("REF-" + System.currentTimeMillis());
        order.setStatusUpdatedAt(LocalDateTime.now(applicationClock));

        cartItemRepository.deleteAll(cart.getItems());
        cart.emptyCart();
        cartRepository.saveAndFlush(cart);
        return orderRepository.saveAndFlush(order);
    }

    @Override
    @Transactional
    public Order cancelOrder(Long userId, Long orderId) {
        Order order = getOrderById(userId, orderId);
        order.setStatus(OrderStatus.CANCELLED);
        order.setStatusUpdatedAt(LocalDateTime.now(applicationClock));
        return orderRepository.saveAndFlush(order);
    }

}
