package com.movie.springmovieproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringMovieProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMovieProjectApplication.class, args);
    }

}
