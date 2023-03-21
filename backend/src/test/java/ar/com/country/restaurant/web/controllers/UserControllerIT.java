package ar.com.country.restaurant.web.controllers;

import ar.com.country.restaurant.AbstractIntegrationTest;
import ar.com.country.restaurant.dao.entities.User;
import ar.com.country.restaurant.dao.entities.UserRole;
import ar.com.country.restaurant.repositories.UserRepository;
import ar.com.country.restaurant.utils.JsonUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql("/user/data.sql")
@Sql(value = "/user/clear-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UserControllerIT extends AbstractIntegrationTest {
    @Autowired
    private UserRepository userRepository;

    @Override
    protected User getRegisteredUserForLogin() {
        User firstSavedUser = getFirstSavedUser();
        firstSavedUser.setPassword("12345678");
        return firstSavedUser;
    }

    @Nested
    class GetUser {

        @Test
        void shouldReturnUserById() throws Exception {
            doLogin();
            User user = getFirstSavedUser();

            mockMvc.perform(
                            get("/api/users/" + user.getId())
                                    .headers(authHeader())
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaTypes.HAL_JSON_VALUE))
                    .andExpect(jsonPath("$.id").value(user.getId()))
                    .andExpect(jsonPath("$.name").value(user.getName()))
                    .andExpect(jsonPath("$.email").value(user.getEmail()))
                    .andExpect(jsonPath("$._links.addresses.href").value("http://localhost/api/addresses"))
                    .andExpect(jsonPath("$._links.payment_methods.href").value("http://localhost/api/payment_methods"));
        }

    }

    @Nested
    class UpdateUser {

        @Test
        void shouldUpdateUser_whenValidFields() throws Exception {
            doLogin();
            User firstSavedUser = getFirstSavedUser();
            User updatedUser = User.builder()
                    .name("Julio")
                    .email("bobbyfischer@gmail.com")
                    .role(UserRole.NORMAL)
                    .build();

            mockMvc.perform(
                            put("/api/users/" + firstSavedUser.getId())
                                    .headers(authHeader())
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(updatedUser))
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaTypes.HAL_JSON_VALUE))
                    .andExpect(jsonPath("$.id").value(firstSavedUser.getId()))
                    .andExpect(jsonPath("$.name").value("Julio"))
                    .andExpect(jsonPath("$.email").value("bobbyfischer@gmail.com"))
                    .andExpect(jsonPath("$.role").value("NORMAL"))
                    .andExpect(jsonPath("$._links.addresses.href").value("http://localhost/api/addresses"))
                    .andExpect(jsonPath("$._links.payment_methods.href").value("http://localhost/api/payment_methods"));
        }

        @Test
        void shouldReturn409Conflict_whenUpdatedEmailAlreadyExists() throws Exception {
            doLogin();
            User firstSavedUser = getFirstSavedUser();
            User updatedUserWithEmailTaken = User.builder()
                    .name("Julio")
                    .email("nicolas.hiking@gmail.com")
                    .role(UserRole.NORMAL)
                    .build();

            mockMvc.perform(
                            put("/api/users/" + firstSavedUser.getId())
                                    .headers(authHeader())
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(updatedUserWithEmailTaken))
                    )
                    .andExpect(status().isConflict());
        }

        @Test
        void shouldReturn409Conflict_whenUpdatedDniAlreadyExists() throws Exception {
            doLogin();
            User firstSavedUser = getFirstSavedUser();
            User updatedUserWithDniTaken = User.builder()
                    .name("Julio")
                    .email("nicolas.hiking@gmail.com")
                    .role(UserRole.NORMAL)
                    .build();

            mockMvc.perform(
                            put("/api/users/" + firstSavedUser.getId())
                                    .headers(authHeader())
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(updatedUserWithDniTaken))
                    )
                    .andExpect(status().isConflict());
        }

        @Test
        void shouldReturn403_whenTryingToUpdateAnotherUser() throws Exception {
            doLogin();
            long anotherUserId = 2L;
            User updatedUser = User.builder()
                    .name("Julio")
                    .email("bobbyfischer@gmail.com")
                    .role(UserRole.NORMAL)
                    .build();

            mockMvc.perform(
                            put("/api/users/" + anotherUserId)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(updatedUser))
                                    .headers(authHeader())
                    )
                    .andExpect(status().isForbidden());
        }

    }

    @Nested
    class DeleteUser {

        @Test
        void shouldDeleteUser() throws Exception {
            doLogin();
            User firstSavedUser = getFirstSavedUser();

            mockMvc.perform(
                            delete("/api/users/" + firstSavedUser.getId())
                                    .headers(authHeader())
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaTypes.HAL_JSON_VALUE))
                    .andExpect(jsonPath("$.id").exists());
        }

        @Test
        void shouldReturn403_whenTryingToDeleteAnotherUser() throws Exception {
            doLogin();
            long anotherUserId = 2L;

            mockMvc.perform(
                            delete("/api/users/" + anotherUserId)
                                    .headers(authHeader())
                    )
                    .andExpect(status().isForbidden());
        }

    }

    public User getFirstSavedUser() {
        return userRepository.findAll().get(0);
    }

}
