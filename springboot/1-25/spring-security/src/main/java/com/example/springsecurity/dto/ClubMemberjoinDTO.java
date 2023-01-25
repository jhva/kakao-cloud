package com.example.springsecurity.dto;


import lombok.Data;

@Data
public class ClubMemberjoinDTO {

    private String mid;
    private String name;
    private String mpw;
    private String email;
    private boolean del;
    private boolean social;


}
