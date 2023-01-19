package com.movie.springmovieproject.domain;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Employe {
    private String emId;
    private String firstname;
    private String secondName;

}
