package ru.repp.den;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;

@SpringBootApplication
@EnableJpaRepositories("ru.repp.den.repo")
public class RooxApplication {

    public static final String BASE_PATH = "avatars" + File.separator;

    public static void main(String[] args) {
		SpringApplication.run(RooxApplication.class, args);
	}
}
