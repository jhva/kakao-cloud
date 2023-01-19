package com.movie.springmovieproject.service;


import com.movie.springmovieproject.dto.ReviewDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    //영화번호에  해당하는 댓글 목록을 처리
    @GetMapping("/{mno}/list")
    public ResponseEntity<List<ReviewDTO>> list(@PathVariable("mno") Long nmo) {
        List<ReviewDTO> result = reviewService.getList(nmo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //댓글 추가
    @PostMapping("/{mno}")
    public ResponseEntity<Long> addComment(@PathVariable("mno") Long mno, @RequestBody ReviewDTO reviewDTO) {
        Long reviewnum = reviewService.register(reviewDTO);
        return new ResponseEntity<>(reviewnum, HttpStatus.OK);
    }

    //댓글  수정
    @PutMapping("/{mno}/{reviewnum}")
    public ResponseEntity<Long> addReview(@PathVariable("mno") Long mno, @PathVariable("reviewnum") Long reviewnum, @RequestBody ReviewDTO reviewDTO) {

        Long reviewNum = reviewService.modify(reviewDTO);


        return new ResponseEntity<>(reviewNum, HttpStatus.OK);
    }

    //댓글삭제
    @DeleteMapping("/{reviewnum}")
    public ResponseEntity<Long> addReview(@PathVariable("reviewnum") Long reviewnum) {

        Long reviewNum = reviewService.remove(reviewnum);


        return new ResponseEntity<>(reviewNum, HttpStatus.OK);
    }

}
