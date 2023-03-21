package ar.com.country.restaurant.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class TokenEncoderClockConfig {

    @Bean
    public Clock tokenEncoderClock() {
        return Clock.systemDefaultZone();
    }

}
