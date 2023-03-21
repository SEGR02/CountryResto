package ar.com.country.restaurant.web.controllers;

import ar.com.country.restaurant.AbstractIntegrationTest;
import ar.com.country.restaurant.dao.entities.DishCategory;
import ar.com.country.restaurant.dao.entities.User;
import ar.com.country.restaurant.dao.entities.UserRole;
import ar.com.country.restaurant.repositories.DishCategoryRepository;
import ar.com.country.restaurant.repositories.UserRepository;
import ar.com.country.restaurant.services.UserService;
import ar.com.country.restaurant.utils.JsonUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql("/category/data.sql")
@Sql(value = "/category/clear-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class DishCategoryControllerIT extends AbstractIntegrationTest {
    @Autowired
    private DishCategoryRepository dishCategoryRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User adminUser = getRegisteredUserForLogin();
        userService.createUser(adminUser);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Override
    protected User getRegisteredUserForLogin() {
        User registeredUserForLogin = super.getRegisteredUserForLogin();
        registeredUserForLogin.setRole(UserRole.ADMIN);
        return registeredUserForLogin;
    }

    @Nested
    public class GetDishCategories {

        @Test
        void shouldReturnAllCategories() throws Exception {
            mockMvc.perform(
                            get("/api/categories")
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$._embedded.dishCategories.size()").value(5));
        }

        @Test
        void shouldReturnCategoryById() throws Exception {
            DishCategory firstSavedCategory = getFirstSavedCategory();

            mockMvc.perform(
                            get("/api/categories/" + firstSavedCategory.getId())
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(firstSavedCategory.getId()))
                    .andExpect(jsonPath("$.name").value(firstSavedCategory.getName()));
        }

        @Test
        void shouldReturn404_whenCategoryNotFound() throws Exception {
            long noSuchCategoryId = -50L;

            mockMvc.perform(
                            get("/api/categories/" + noSuchCategoryId)
                    )
                    .andExpect(status().isNotFound());
        }

    }

    @Nested
    public class CreateDishCategory {

        @Test
        void shouldCreateCategory_whenValidFields() throws Exception {
            doLogin();
            DishCategory newDishCategory = DishCategory.builder().name("New Category").build();

            mockMvc.perform(
                            multipart(HttpMethod.POST, "/api/categories")
                                    .file(new MockMultipartFile(
                                            "category",
                                            "category.json",
                                            MediaType.APPLICATION_JSON_VALUE,
                                            JsonUtils.asJsonString(newDishCategory).getBytes()))
                                    .headers(authHeader())
                    )
                    .andExpect(status().isCreated())
                    .andExpect(header().exists("Location"))
                    .andExpect(jsonPath("$.id").isNumber())
                    .andExpect(jsonPath("$.name").value("New Category"));
        }

        @Test
        void shouldReturn400_whenInvalidFields() throws Exception {
            doLogin();
            DishCategory dishCategoryWithMissingNameField = DishCategory.builder().build();

            mockMvc.perform(
                            multipart(HttpMethod.POST, "/api/categories")
                                    .file(new MockMultipartFile(
                                            "category",
                                            "category.json",
                                            MediaType.APPLICATION_JSON_VALUE,
                                            JsonUtils.asJsonString(dishCategoryWithMissingNameField).getBytes()
                                    ))
                                    .headers(authHeader())
                    )
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturn409Conflict_whenCategoryAlreadyExists() throws Exception {
            doLogin();
            DishCategory firstSavedCategory = getFirstSavedCategory();
            DishCategory newDishCategoryWithDuplicatedName = DishCategory.builder()
                    .name(firstSavedCategory.getName())
                    .build();

            mockMvc.perform(
                            multipart(HttpMethod.POST, "/api/categories")
                                    .file(new MockMultipartFile(
                                            "category",
                                            "category.json",
                                            MediaType.APPLICATION_JSON_VALUE,
                                            JsonUtils.asJsonString(newDishCategoryWithDuplicatedName).getBytes()
                                    ))
                                    .headers(authHeader())
                    )
                    .andExpect(status().isConflict());
        }

    }

    @Nested
    class UpdateDishCategory {

        @Test
        void shouldUpdateCategory_whenValidFields() throws Exception {
            doLogin();
            DishCategory firstSavedCategory = getFirstSavedCategory();
            DishCategory updatedCategory = DishCategory.builder().name("Updated Category").build();

            mockMvc.perform(
                            multipart(HttpMethod.PUT, "/api/categories/" + firstSavedCategory.getId())
                                    .file(new MockMultipartFile(
                                            "category",
                                            "category.json",
                                            MediaType.APPLICATION_JSON_VALUE,
                                            JsonUtils.asJsonString(updatedCategory).getBytes()
                                    ))
                                    .headers(authHeader())
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").isNumber())
                    .andExpect(jsonPath("$.name").value("Updated Category"));
        }

        @Test
        void shouldReturn400_whenInvalidFields() throws Exception {
            doLogin();
            DishCategory firstSavedCategory = getFirstSavedCategory();
            DishCategory updatedCategory = DishCategory.builder().build();

            mockMvc.perform(
                            multipart(HttpMethod.PUT, "/api/categories/" + firstSavedCategory.getId())
                                    .file(new MockMultipartFile(
                                            "category",
                                            "category.json",
                                            MediaType.APPLICATION_JSON_VALUE,
                                            JsonUtils.asJsonString(updatedCategory).getBytes()
                                    ))
                                    .headers(authHeader())
                    )
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturn404_whenCategoryNotFound() throws Exception {
            doLogin();
            long noSuchCategoryId = -50L;
            DishCategory updatedCategory = DishCategory.builder().name("Updated Category").build();

            mockMvc.perform(
                            multipart(HttpMethod.PUT, "/api/categories/" + noSuchCategoryId)
                                    .file(new MockMultipartFile(
                                            "category",
                                            "category.json",
                                            MediaType.APPLICATION_JSON_VALUE,
                                            JsonUtils.asJsonString(updatedCategory).getBytes()
                                    ))
                                    .headers(authHeader())
                    )
                    .andExpect(status().isNotFound());
        }

    }

    @Nested
    class DeleteDishCategory {

        @Test
        void shouldDeleteCategory_whenCategoryExists() throws Exception {
            doLogin();
            DishCategory firstSavedCategory = getFirstSavedCategory();

            mockMvc.perform(
                            delete("/api/categories/" + firstSavedCategory.getId())
                                    .headers(authHeader())
                    )
                    .andExpect(status().isOk());
        }

        @Test
        void shouldReturn404_whenCategoryNotFound() throws Exception {
            doLogin();
            long noSuchCategoryId = -50L;

            mockMvc.perform(
                            delete("/api/categories/" + noSuchCategoryId)
                                    .headers(authHeader())
                    )
                    .andExpect(status().isNotFound());
        }

    }

    private DishCategory getFirstSavedCategory() {
        List<DishCategory> categories = dishCategoryRepository.findAll();
        return categories.get(0);
    }

}
