package ru.repp.den.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Den on 21.02.2016.
 */
@RestController

public class HelloController {

    @RequestMapping("/")
    public String sayHello() {
        return "Greetings from Spring Boot!";
    }
}
