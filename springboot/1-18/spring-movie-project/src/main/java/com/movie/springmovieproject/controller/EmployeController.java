package com.movie.springmovieproject.controller;


import com.movie.springmovieproject.domain.Employe;
import com.movie.springmovieproject.service.EmployesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
public class EmployeController {

    private final EmployesService employesService;

    @GetMapping("/add/employee")
    public ResponseEntity<Employe> addEmployee(
            @RequestParam("empId") String empId,
            @RequestParam("firstName") String firstName,
            @RequestParam("secondName") String secondName
    ) {
        Employe employe = employesService.createEmployee(
                empId, firstName, secondName
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(employe, headers, HttpStatus.OK);
    }
}
