package ar.com.country.restaurant.web.controllers;

import ar.com.country.restaurant.AbstractIntegrationTest;
import ar.com.country.restaurant.dao.entities.User;
import ar.com.country.restaurant.dao.entities.UserRole;
import ar.com.country.restaurant.repositories.UserRepository;
import ar.com.country.restaurant.services.UserService;
import ar.com.country.restaurant.utils.JsonUtils;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LoginAndRegistrationControllerIT extends AbstractIntegrationTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtDecoder accessTokenDecoder;

    @Autowired
    @Qualifier("jwtRefreshTokenDecoder")
    private JwtDecoder refreshTokenDecoder;

    @MockBean(name = "tokenEncoderClock")
    private Clock clock;
    private User user;

    @BeforeEach
    void init() {
        user = User.builder()
                .name("Julio")
                .email("julion.alvarez@gmail.com")
                .password("12345678")
                .role(UserRole.NORMAL)
                .build();
        String unencryptedPassword = user.getPassword();
        userService.createUser(user);
        user.setPassword(unencryptedPassword);

        Clock defaultClock = Clock.systemDefaultZone();
        given(clock.instant()).willReturn(defaultClock.instant());
        given(clock.getZone()).willReturn(defaultClock.getZone());
    }

    @AfterEach
    void deleteUsers() {
        userRepository.deleteAll();
    }

    @Nested
    class Login {

        @Test
        void shouldLogin_whenValidCredentials() throws Exception {
            String response = mockMvc.perform(
                            post("/api/login")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(getRegisteredUserForLogin()))
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").exists())
                    .andExpect(jsonPath("$.email").value("julion.alvarez@gmail.com"))
                    .andExpect(jsonPath("$.role").value("NORMAL"))
                    .andExpect(jsonPath("$.accessToken").exists())
                    .andExpect(jsonPath("$.refreshToken").exists())
                    .andReturn()
                    .getResponse().getContentAsString();

            String accessToken = JsonPath.read(response, "$.accessToken");
            String refreshToken = JsonPath.read(response, "$.refreshToken");

            assertValidAccessToken(accessToken);
            assertValidRefreshToken(refreshToken);
        }

        @Test
        void shouldReturn400_whenEmailNotFound() throws Exception {
            User user = getRegisteredUserForLogin();
            user.setEmail("not.found.email@gmail.com");

            mockMvc.perform(
                            post("/api/login")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(user)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturn400_whenIncorrectPassword() throws Exception {
            User user = getRegisteredUserForLogin();
            user.setPassword("invalid-password");

            mockMvc.perform(
                            post("/api/login")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(user))
                    )
                    .andExpect(status().isBadRequest());
        }

    }

    @Nested
    class Register {

        @Test
        void shouldRegister_whenValidCredentials() throws Exception {
            User newUser = createUnregisteredDummyUser();

            String response = mockMvc.perform(
                            post("/api/register")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(newUser))
                    )
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").exists())
                    .andExpect(jsonPath("$.email").value("ricardoibarra2044@gmail.com"))
                    .andExpect(jsonPath("$.role").value("NORMAL"))
                    .andExpect(jsonPath("$.accessToken").exists())
                    .andExpect(jsonPath("$.refreshToken").exists())
                    .andReturn()
                    .getResponse().getContentAsString();

            String accessToken = JsonPath.read(response, "$.accessToken");
            String refreshToken = JsonPath.read(response, "$.refreshToken");

            assertValidAccessToken(accessToken);
            assertValidRefreshToken(refreshToken);
        }

        @Test
        void shouldReturn400_whenMissingFields() throws Exception {
            User userWithMissingFields = User.builder()
                    .name("Nicolás")
                    .email("nicolas.hiking@gmail.com")
                    .build();

            mockMvc.perform(
                            post("/api/register")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(userWithMissingFields))
                    )
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturn409ConflictStatusCode_whenEmailAlreadyTaken() throws Exception {
            User userWithEmailAlreadyTaken = createUnregisteredDummyUser();
            userWithEmailAlreadyTaken.setEmail("julion.alvarez@gmail.com");

            mockMvc.perform(
                            post("/api/register")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(userWithEmailAlreadyTaken))
                    )
                    .andExpect(status().isConflict());
        }

        @Test
        void shouldReturn400_whenEmailIsInvalid() throws Exception {
            User userWithInvalidEmail = createUnregisteredDummyUser();
            userWithInvalidEmail.setEmail("invalid_email@");

            mockMvc.perform(
                            post("/api/register")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(userWithInvalidEmail))
                    )
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturn400_whenPasswordLess6CharsOrMore16Chars() throws Exception {
            User userPasswordLess6Chars = createUnregisteredDummyUser();
            userPasswordLess6Chars.setPassword("passw");

            mockMvc.perform(
                            post("/api/register")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(userPasswordLess6Chars))
                    )
                    .andExpect(status().isBadRequest());

            User userPasswordMore16Chars = createUnregisteredDummyUser();
            userPasswordMore16Chars.setPassword("very-very-very-large-password");

            mockMvc.perform(
                            post("/api/register")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(userPasswordMore16Chars))
                    )
                    .andExpect(status().isBadRequest());
        }

    }

    @Nested
    class RefreshToken {

        @Test
        void shouldReturnNewAccessToken_andKeepSameRefreshToken() throws Exception {
            doLogin();
            Map<String, String> body = Map.of("refreshToken", refreshToken);
            // After logging, we must wait at least 1 second
            // before calling the "refresh-token" endpoint.
            given(clock.instant()).willReturn(Instant.now().plusSeconds(1));
            given(clock.getZone()).willReturn(Clock.systemDefaultZone().getZone());

            String response = mockMvc.perform(
                            post("/api/refresh-token")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(body))
                    )
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse().getContentAsString();

            String newAccessToken = JsonPath.read(response, "$.accessToken");
            String responseRefreshToken = JsonPath.read(response, "$.refreshToken");

            assertValidAccessToken(newAccessToken);
            assertThat(newAccessToken).isNotEqualTo(accessToken);

            assertValidRefreshToken(responseRefreshToken);
            assertThat(responseRefreshToken).isEqualTo(refreshToken);
        }

        @Test
        void shouldReturnNewRefreshToken_whenLessThan1WeekBeforeExpiration() throws Exception {
            doLogin();
            // Travel 6 days to the future. Refresh token has 4 more days until expiration
            LocalDate timeToTheFuture = LocalDate.now().plusDays(6);
            ZoneId zoneId = ZoneId.systemDefault();
            given(clock.instant()).willReturn(timeToTheFuture.atStartOfDay(zoneId).toInstant());
            given(clock.getZone()).willReturn(zoneId);
            Map<String, String> body = Map.of("refreshToken", refreshToken);

            String response = mockMvc.perform(
                            post("/api/refresh-token")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(body))
                    )
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse().getContentAsString();

            String newAccessToken = JsonPath.read(response, "$.accessToken");
            String responseRefreshToken = JsonPath.read(response, "$.refreshToken");

            assertValidAccessToken(newAccessToken);
            assertThat(newAccessToken).isNotEqualTo(accessToken);

            assertValidRefreshToken(responseRefreshToken);
            assertThat(responseRefreshToken).isNotEqualTo(refreshToken);
        }

    }

    private void assertValidAccessToken(String token) {
        assertValidToken(token, accessTokenDecoder);
    }

    private void assertValidRefreshToken(String token) {
        assertValidToken(token, refreshTokenDecoder);
    }

    private void assertValidToken(String token, JwtDecoder decoder) {
        try {
            decoder.decode(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected User getRegisteredUserForLogin() {
        return user;
    }

    private User createUnregisteredDummyUser() {
        return User.builder()
                .name("Nicolás")
                .email("ricardoibarra2044@gmail.com")
                .password("password12345")
                .role(UserRole.NORMAL)
                .build();
    }

}
