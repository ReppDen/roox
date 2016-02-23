package ru.repp.den;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
(exclude = {
		org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class
})
@EnableJpaRepositories("ru.repp.den.repo")
//@EnableResourceServer
public class RooxApplication {

//    @Bean
//    public WebMvcConfigurerAdapter forwardToIndex() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addViewControllers(ViewControllerRegistry registry) {
//                registry.addViewController("/").setViewName(
//                        "forward:/index.html");
//            }
//        };
//    }

    public static void main(String[] args) {
		SpringApplication.run(RooxApplication.class, args);
	}

    /** todo list:
     * thymeleaf in POM
     * get current user in User service
     * exception handler
     * move convertations to rest
     * avatar saving
     *
     */
}
