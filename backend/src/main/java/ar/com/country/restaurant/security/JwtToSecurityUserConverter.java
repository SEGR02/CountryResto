package ar.com.country.restaurant.security;

import ar.com.country.restaurant.dao.entities.User;
import ar.com.country.restaurant.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtToSecurityUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {
    private final UserService userService;

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
        String username = jwt.getSubject();
        User user = userService.getUserByEmail(username);
        SecurityUser securityUser = new SecurityUser(user);
        return new UsernamePasswordAuthenticationToken(securityUser, jwt, securityUser.getAuthorities());
    }

}
