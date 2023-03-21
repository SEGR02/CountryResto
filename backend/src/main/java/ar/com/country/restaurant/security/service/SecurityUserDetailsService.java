package ar.com.country.restaurant.security.service;

import ar.com.country.restaurant.dao.entities.User;
import ar.com.country.restaurant.exceptions.UserNotFoundException;
import ar.com.country.restaurant.security.SecurityUser;
import ar.com.country.restaurant.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        try {
            User user = userService.getUserByEmail(email);
            return new SecurityUser(user);
        } catch (UserNotFoundException e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }

}
