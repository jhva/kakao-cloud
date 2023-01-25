package com.example.springsecurity.repository;

import com.example.springsecurity.dto.ClubMemberjoinDTO;
import com.example.springsecurity.dto.ClubMembmerSecurityDTO;

public interface MemberService {

    static class MidExistException extends Exception {

    }

    void join(ClubMemberjoinDTO clubMemberjoinDTO) throws MidExistException;
}
