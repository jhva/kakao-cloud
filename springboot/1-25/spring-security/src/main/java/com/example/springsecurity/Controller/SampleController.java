package com.example.springsecurity.Controller;


import com.example.springsecurity.dto.ClubMembmerSecurityDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class SampleController {


    @GetMapping("/")
    public String index() {
        log.info("apdls");
        return "/index";

    }

    //로그인한 유저만 접속이가능
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/sample/member")
    public void member(@AuthenticationPrincipal ClubMembmerSecurityDTO clubMembmerSecurityDTO) {
        log.info("멤버 만 허용");

        Authentication authentication
                = SecurityContextHolder.getContext().getAuthentication();
        log.info("로그인한유저" +
                clubMembmerSecurityDTO.getName());

    }

    @GetMapping("/sample/all")
    public void main() {
        log.info("모두허용");
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/sample/admin")
    public void admin() {
        log.info("관리자 만 허용");
    }
}
