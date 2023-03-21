package ar.com.country.restaurant.bootstrap;

import ar.com.country.restaurant.dao.entities.User;
import ar.com.country.restaurant.dao.entities.UserRole;
import ar.com.country.restaurant.exceptions.UserNotFoundException;
import ar.com.country.restaurant.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Setter
@RequiredArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "application.admin")
@Profile({"dev", "admin"})
public class AdminAccountInitializer implements ApplicationRunner {
    private final UserService userService;
    private String defaultName;
    private String defaultEmail;
    private String defaultPassword;

    @Override
    public void run(ApplicationArguments args) {
        createDefaultAdminUserIfNotExists();
        printDefaultAdminUserCredentials();
    }

    private void createDefaultAdminUserIfNotExists() {
        if (adminAccountCreated()) {
            return;
        }
        User adminUser = User.builder()
                .name(defaultName)
                .email(defaultEmail)
                .password(defaultPassword)
                .role(UserRole.ADMIN)
                .build();
        userService.createUser(adminUser);
    }

    private boolean adminAccountCreated() {
        try {
            userService.getUserByEmail(defaultEmail);
            return true;
        } catch (UserNotFoundException e) {
            return false;
        }
    }

    private void printDefaultAdminUserCredentials() {
        System.out.printf("Default admin email: %s%n", defaultEmail);
        System.out.printf("Default admin password: %s%n", defaultPassword);
    }

}
