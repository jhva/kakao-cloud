package com.example.springbootpractice;

import com.example.springbootpractice.domain.GuestBook;
import com.example.springbootpractice.domain.GuestBookDTO;
import com.example.springbootpractice.domain.PageRequestDTO;
import com.example.springbootpractice.domain.PageResponseDTO;
import com.example.springbootpractice.repository.GuestBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@RestController
@RequiredArgsConstructor
public class VIewwController {
    private final GuestBookService guestBookService;

    @GetMapping("/guestbook/list.json")
    public PageResponseDTO<GuestBookDTO, GuestBook> list(
            PageRequestDTO pageRequestDTO) {
        return guestBookService.getList(pageRequestDTO);
    }

    @GetMapping("/guestbook/register")
    public void register() {
        log.info("데이터 삽입 요청");
    }


    @PostMapping("/guestbook/register")
    public String register(
            GuestBookDTO dto, RedirectAttributes rattr
    ) {
        log.info(dto); //파라미터 확인
        Long gno = guestBookService.register(dto);
        //RedirectAttributes 는 세션에 저장하는데 한 번 사용하고 자동 소멸
        //데이터 전송
        rattr.addFlashAttribute(
                "msg", gno + "등록");
        //데이터베이스에 변경 작업을 수행하고 나면 반드시 redirect
        return "redirect:/guestbook/list";
    }


}
