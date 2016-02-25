package ru.repp.den;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("ru.repp.den.repo")
public class RooxApplication {

    public static void main(String[] args) {
		SpringApplication.run(RooxApplication.class, args);
	}

    /** todo list:
     *
     * use token i nsecurity
     * exception handler
     * move convertations to rest
     * avatar saving
     *
     */
}
