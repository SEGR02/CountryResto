package ar.com.country.restaurant;

import ar.com.country.restaurant.dao.entities.User;
import ar.com.country.restaurant.dao.entities.UserRole;
import ar.com.country.restaurant.utils.JsonUtils;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AbstractIntegrationTest {
    @Autowired
    protected MockMvc mockMvc;
    protected String accessToken;
    protected String refreshToken;

    protected void doLogin() throws Exception {
        User registeredUser = getRegisteredUserForLogin();
        var credentials = Map.of("email", registeredUser.getEmail(), "password", registeredUser.getPassword());
        String response = mockMvc.perform(
                        post("/api/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.asJsonString(credentials))
                )
                .andReturn()
                .getResponse().getContentAsString();

        accessToken = JsonPath.read(response, "$.accessToken");
        refreshToken = JsonPath.read(response, "$.refreshToken");
    }

    protected User getRegisteredUserForLogin() {
        return User.builder()
                .name("Julio")
                .email("julion.alvarez@gmail.com")
                .password("12345678")
                .role(UserRole.NORMAL)
                .build();
    }

    protected HttpHeaders authHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        return httpHeaders;
    }

}
