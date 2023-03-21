package ar.com.country.restaurant.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.validation.ConstraintViolationProblemModule;

@Configuration
public class JacksonConfig {
    @Value("${response.show.stacktrace:false}")
    private boolean showStackTraces;

    @Bean
    public ProblemModule problemModule() {
        return new ProblemModule().withStackTraces(showStackTraces);
    }

    @Bean
    public ConstraintViolationProblemModule constraintViolationProblemModule() {
        return new ConstraintViolationProblemModule();
    }

}
