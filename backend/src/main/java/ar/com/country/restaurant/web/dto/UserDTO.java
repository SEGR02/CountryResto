package ar.com.country.restaurant.web.dto;

import ar.com.country.restaurant.dao.entities.UserRole;
import ar.com.country.restaurant.web.dto.validation.OnCreate;
import ar.com.country.restaurant.web.dto.validation.Password;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@Relation(itemRelation = "user", collectionRelation = "users")
public final class UserDTO extends RepresentationModel<UserDTO> {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final Long id;

    @NotBlank
    private final String name;

    @Email
    @NotBlank
    private final String email;

    @Password
    @NotBlank(groups = OnCreate.class)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private final String password;

    @NotNull
    private final UserRole role;
}
