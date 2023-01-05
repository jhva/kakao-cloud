package com.example.controller;


import com.example.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/*
 컨트롤러는 다른 클래스에 주입되지 않기 때문에
 템플릿 메서드 패턴을 사용하지 않음
 */
@Controller
@RequiredArgsConstructor
public class MemberController {


    @Autowired
    private MemberService memberService;

    public void findById(String id) {
        System.out.println(memberService.findById(id));
    }

}
