package ar.com.country.restaurant.services;

import ar.com.country.restaurant.dao.entities.order.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrdersOfUser(Long userId);

    Order getOrderById(Long userId, Long orderId);

    Order createOrder(Long userId, Long paymentMethodId);

    Order cancelOrder(Long userId, Long orderId);

}
