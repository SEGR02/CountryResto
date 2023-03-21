package ar.com.country.restaurant.services;

import ar.com.country.restaurant.dao.entities.Cart;
import ar.com.country.restaurant.dao.entities.CartItem;

public interface CartService {

    Cart getCartOfUser(Long userId);

    CartItem addCartItem(Long userId, Long dishId, CartItem cartItem);

    CartItem deleteCartItem(Long userId, Long itemId);

}
