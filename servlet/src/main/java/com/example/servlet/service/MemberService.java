package com.example.servlet.service;

import com.example.servlet.MemberDTO;

public interface MemberService {


    MemberDTO login(String uuid);


    MemberDTO login(
            String mid, String mpw, String uuid);
    //자동 로그인 처리를 위한 메서드

}