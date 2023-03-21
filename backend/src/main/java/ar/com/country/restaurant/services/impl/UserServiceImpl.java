package ar.com.country.restaurant.services.impl;

import ar.com.country.restaurant.dao.entities.User;
import ar.com.country.restaurant.exceptions.EmailAlreadyTakenException;
import ar.com.country.restaurant.exceptions.UserNotFoundException;
import ar.com.country.restaurant.repositories.UserRepository;
import ar.com.country.restaurant.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public User createUser(User user) {
        ensureUniqueEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User updatedUser) {
        User user = getUserById(userId);
        if (!user.getEmail().equals(updatedUser.getEmail())) {
            ensureUniqueEmail(updatedUser.getEmail());
        }

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setRole(updatedUser.getRole());

        return userRepository.save(user);
    }

    @Override
    public User deleteUser(Long userId) {
        User userToDelete = getUserById(userId);
        userRepository.deleteById(userId);
        return userToDelete;
    }

    private void ensureUniqueEmail(String email) {
        boolean emailTaken = userRepository.existsByEmail(email);
        if (emailTaken) {
            throw new EmailAlreadyTakenException(email);
        }
    }

}
