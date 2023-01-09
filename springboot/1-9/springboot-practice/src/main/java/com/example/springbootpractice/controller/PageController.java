package com.example.springbootpractice.controller;

import com.example.springbootpractice.domain.SampleVO;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
public class PageController {
    private final org.slf4j.Logger Logger = LoggerFactory.getLogger(PageController.class);

    @GetMapping("/")
    public String main(Model model) {
        Map<String, Object> map = new HashMap<>();

        map.put("msg", "java");
        map.put("buildTools", "gradle");
        model.addAttribute("msg", map);

        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("쏘ㅑㄹ랴쏘ㅑㄹㄹ랴");
        model.addAttribute("list", list);
        return "main";
    }

    @GetMapping("/ex1")
    //리턴 타입이 void 이면 출력하는 뷰 이름은 요청 url
    //view 의 이름은 ex1
    public void ex1(Model model) {
        Logger.info("ex1");

//        return "123";
//        Logger.info("ex1");
    }


    @GetMapping("/ex2")
    public void ex2(Model model) {
        //1~부터 20
        List<SampleVO> list = IntStream.range(1, 20).asLongStream().mapToObj(i -> {
            //정수를 객체로 바꾸겠다는의미이다
            SampleVO vo = SampleVO.builder()
                    .sno(i)
                    .first("..FIRST" + i)
                    .last("LAST.." + i)
                    .regTime(LocalDateTime.now())
                    .build();
            return vo;
            // 1~20 까지 의 숫자를
            //Sample VO로 바꾼후

//이러면 리스트로바뀜
        }).collect(Collectors.toList());
        model.addAttribute("list", list);
    }

}
