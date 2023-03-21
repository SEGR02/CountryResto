package ar.com.country.restaurant.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/api/hi")
    public String hi() {
        return "Hi!";
    }

}
