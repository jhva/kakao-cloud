package org.example;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping("/message/detail/{num}")
    public ModelAndView detail(@PathVariable int num) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("detail");
        mav.addObject("num", num);
        return mav;
    }
}
