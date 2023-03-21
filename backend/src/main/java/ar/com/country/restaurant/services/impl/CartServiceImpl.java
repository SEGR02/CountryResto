package ar.com.country.restaurant.services.impl;

import ar.com.country.restaurant.dao.entities.Cart;
import ar.com.country.restaurant.dao.entities.CartItem;
import ar.com.country.restaurant.dao.entities.Dish;
import ar.com.country.restaurant.dao.entities.User;
import ar.com.country.restaurant.repositories.CartItemRepository;
import ar.com.country.restaurant.repositories.CartRepository;
import ar.com.country.restaurant.services.CartService;
import ar.com.country.restaurant.services.DishService;
import ar.com.country.restaurant.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final UserService userService;
    private final DishService dishService;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public Cart getCartOfUser(Long userId) {
        User user = userService.getUserById(userId);
        return cartRepository.save(user.createAndGetCart());
    }

    @Override
    @Transactional
    public CartItem addCartItem(Long userId, Long dishId, CartItem cartItem) {
        User user = userService.getUserById(userId);
        Dish dish = dishService.getDishById(dishId);
        Cart cart = user.createAndGetCart();

        cartItem.setDish(dish);
        cart.addCartItem(cartItem);

        return cartItemRepository.saveAndFlush(cartItem);
    }

    @Override
    @Transactional
    public CartItem deleteCartItem(Long userId, Long itemId) {
        User user = userService.getUserById(userId);
        Cart cart = user.createAndGetCart();
        CartItem cartItem = cart.getCartItemById(itemId);

        cart.removeCartItem(itemId);
        cartRepository.save(cart);

        return cartItem;
    }

}
