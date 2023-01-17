package com.movie.springmovieproject.controller;


import com.movie.springmovieproject.dto.MovieDTO;
import com.movie.springmovieproject.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movie")
@Log4j2
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
//
//
//    @PostMapping("")
//    public String register(MovieDTO movieDTO) {
//    }

    @GetMapping("/register")
    public void register() {
    }
}
