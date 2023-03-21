package ar.com.country.restaurant.services;

import ar.com.country.restaurant.dao.entities.User;

public interface UserService {

    User getUserByEmail(String email);

    User getUserById(Long id);

    User createUser(User user);

    User updateUser(Long userId, User updatedUser);

    User deleteUser(Long id);

}
