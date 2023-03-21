package ar.com.country.restaurant;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CountryRestaurantApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountryRestaurantApplication.class, args);
    }

}
