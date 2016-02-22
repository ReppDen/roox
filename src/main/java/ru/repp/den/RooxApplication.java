package ru.repp.den;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
		org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class
})
@EnableJpaRepositories("ru.repp.den.repo")
public class RooxApplication {

    @Bean
    public WebMvcConfigurerAdapter forwardToIndex() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName(
                        "forward:/index.html");
            }
        };
    }

	public static void main(String[] args) {
		SpringApplication.run(RooxApplication.class, args);
	}

    /** todo list:
     * thymeleaf in POM
     * current user in Customer Service
     * exception handler
     * move convertations to rest
     *
     */
}
