package com.example.webapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  /*  @GetMapping("/")
    public String index() {
        return "index";
    }*/

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
