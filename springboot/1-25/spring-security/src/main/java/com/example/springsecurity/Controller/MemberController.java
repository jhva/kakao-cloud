package com.example.springsecurity.Controller;


import com.example.springsecurity.dto.ClubMemberjoinDTO;
import com.example.springsecurity.repository.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    //error는 로그인 실패했을때의 파라미터
    //logout은 로그아웃 한 후 로그인으로 이동했을 대의 파라미터
    public void Login(String error, String logout) {

        if (logout != null) {
            log.info("로그아웃");
        }
    }

    //회원가입 페이지 이돟ㅇ
    @GetMapping("/join")
    public void join() {
        log.info("회원 가입 페이지로 이동 ");
    }


    //회원가이ㅏㅂ 처리
    @PostMapping("/join")
    public String join(ClubMemberjoinDTO clubMemberjoinDTO, RedirectAttributes rattr) {
        log.info("post 회원가입");

        try {
            memberService.join(clubMemberjoinDTO);
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("error", "mid");
            System.out.println(e.getLocalizedMessage());
        }
        rattr.addFlashAttribute("result", "success");
        return "redirect:/";

    }

}
