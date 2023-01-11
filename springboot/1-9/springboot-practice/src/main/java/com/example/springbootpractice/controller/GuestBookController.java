package com.example.springbootpractice.controller;


import com.example.springbootpractice.domain.PageRequestDTO;
import com.example.springbootpractice.repository.GuestBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class GuestBookController {


    private final GuestBookService guestBookService;


    @GetMapping({"/"})
    public String list() {
        return "redirect:/guestbook/list";
    }

    @GetMapping("/guestbook/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("result", guestBookService.getList(pageRequestDTO));
    }
}
