package com.example.springbootpractice.controller;


import com.example.springbootpractice.ParamDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@RequestMapping("/api/v1/rest-api")
@RestController
public class JSONController {

    //로깅 가능한 객체를생성
//    private final Logger LOGGER = (Logger) LoggerFactory.getLogger(JSONController.class);


    @RequestMapping(value = "/hello", method = RequestMethod.GET) //옛날방식
    public String hello() {
//        LOGGER.info("hello dycjd ");
        return "rest-api hello";
    }


    @GetMapping("/newhello")//현재
    public String newhello() {
        return "rest-api newhello";
    }


    @GetMapping("/product/{num}")//현재
    public String newhello(@PathVariable("num") int number) {
        return number + "number";
    }

    @GetMapping("/param")
    public String getParam(HttpServletRequest req) {
        String name = req.getParameter("name");
        String organization = req.getParameter("organization");
        String email = req.getParameter("email");

        return "name: " + name + ", organization: " + organization + "email" + email;


    }

    @GetMapping("/param1")
    public String getParam1(@RequestParam("name") String name,
                            @RequestParam("organization") String organization,
                            @RequestParam("email") String email
    ) {

        return "name: " + name + ", organization: " + organization + "email" + email;

    }

    @GetMapping("/param2")
    public String getParam2(ParamDTO paramDTO) {

        return paramDTO.getName() + ": " +
                paramDTO.getOrganization() + ":" +
                paramDTO.getEmail();

    }

    @PostMapping("/post")
    public String post(@RequestBody ParamDTO paramDTO) {

        return paramDTO.toString();


    }

    @PutMapping("/param3")
    public ResponseEntity<ParamDTO> putParam(@RequestBody ParamDTO paramDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(paramDTO);
    }


    @DeleteMapping("/param3")
    public String deleteParam(@RequestParam("num") int num) {
        return num + "";
    }
}
