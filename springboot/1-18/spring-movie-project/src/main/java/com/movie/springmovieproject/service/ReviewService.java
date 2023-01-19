package com.movie.springmovieproject.service;

import com.movie.springmovieproject.domain.Member;
import com.movie.springmovieproject.domain.Movie;
import com.movie.springmovieproject.domain.Review;
import com.movie.springmovieproject.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    //영화 번호에 해당하는 리뷰를 전부 ㅏㄱ져오기
    List<ReviewDTO> getList(Long mno);

    //리뷰 등록
    Long register(ReviewDTO reviewDTO);


    //리뷰수정
    Long modify(ReviewDTO reviewDTO);

    //리뷰 삭제
    Long remove(Long num);

    //DTO를 ENTITY로 변환해주는 메서드
    default Review dtoToEntity(ReviewDTO reviewDTO) {
        Review review = Review.builder()
                .reviewnum(reviewDTO.getReviewNum())
                .score(reviewDTO.getGrade())
                .text(reviewDTO.getText())
                .movie(Movie.builder().mno(reviewDTO.getMno()).build())
                .member(Member.builder().mid(reviewDTO.getMid()).build())
                .build();
        return review;
    }

    //Entity를 dto로
    default ReviewDTO entityToDTO(Review review) {
        ReviewDTO reviewdto= ReviewDTO.builder()
                .reviewNum(review.getReviewnum())
                .grade(review.getScore())
                .text(review.getText())
                .mid(review.getMember().getMid())
                .email(review.getMember().getEmail())
                .nickname(review.getMember().getNickname())
                .text(review.getText())
                .regDate(review.getRegDate())
                .modDate(review.getModdate())
                .build();

        return reviewdto;
    }
}
