package ar.com.country.restaurant.web.dto.auth;

import ar.com.country.restaurant.web.dto.validation.Password;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record LoginDTO(
        @Email
        @NotBlank(message = "An email is required.")
        String email,

        @Password
        @NotBlank(message = "A password is required.")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password
) {

    @Override
    public String toString() {
        return "LoginDTO{" +
                "email='" + email + '\'' +
                ", password=******** }";
    }

}
