package com.example.springboardproject;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/comment") //공통 url설정
public class CommentController {
    private final CommentService commentService;

    @GetMapping(value = "/board/{bno}")
    public ResponseEntity<List<CommentDTO>> getByBoard(@PathVariable("bno") Long bno) {
        log.info("bno:" + bno);

        return new ResponseEntity<>(commentService.getList(bno), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Long> register(@RequestBody CommentDTO commentDTO) {
        log.info(commentDTO);
        Long rno = commentService.register(commentDTO);
        return new ResponseEntity<>(rno, HttpStatus.OK);
    }

    @DeleteMapping("/{rno")
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
        log.info("rno:" + rno);

        commentService.remove(rno);
        return new ResponseEntity<>(rno + "삭제", HttpStatus.OK);
    }
}
