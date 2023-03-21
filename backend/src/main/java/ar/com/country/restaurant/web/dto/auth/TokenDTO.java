package ar.com.country.restaurant.web.dto.auth;

import ar.com.country.restaurant.dao.entities.UserRole;
import lombok.Builder;

@Builder
public record TokenDTO(
        Long id,
        String email,
        UserRole role,
        String accessToken,
        String refreshToken
) {
}
