package com.movie.springmovieproject.controller;


import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "";
    }
    @GetMapping("/user")
    public void user(){
        System.out.println("컨트롤러");
    }
}
