package ru.repp.den;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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

    /** todo list:
     *
     * use token i nsecurity
     * exception handler
     * move convertations to rest
     * avatar saving
     *
     *
     * file upload
     * file save with new name
     * save the name to the PM
     * rest to get image by PM_ID
     * trash manager
     *
     */
}
