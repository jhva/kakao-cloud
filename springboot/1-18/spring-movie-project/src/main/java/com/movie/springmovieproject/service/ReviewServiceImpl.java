package com.movie.springmovieproject.service;


import com.movie.springmovieproject.domain.Movie;
import com.movie.springmovieproject.domain.Review;
import com.movie.springmovieproject.dto.ReviewDTO;
import com.movie.springmovieproject.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {


    private final ReviewRepository reviewRepository;

    //영화 번호에 해당하는 리뷰를 전부 ㅏㄱ져오기

    @Override
    public List<ReviewDTO> getList(Long mno) {
        Movie movie = Movie.builder().mno(mno)
                .build();
        List<Review> result = reviewRepository.findByMovie(movie);

        log.info("result" + result);


        return result.stream().map(review -> entityToDTO(review)).collect(Collectors.toList());
//        return null;

    }

    @Override
    public Long register(ReviewDTO reviewDTO) {
        //dto니까 entity로 변환하고 시작하자
        Review review = dtoToEntity(reviewDTO);

        reviewRepository.save(review);
        //글 번호 리턴해줄까
        return review.getReviewnum();
    }

    @Override
    public Long modify(ReviewDTO reviewDTO) {
        //dto니까 entity로 변환하고 시작하자
        Review review = dtoToEntity(reviewDTO);

        reviewRepository.save(review);
        //글 번호 리턴해줄까
        return review.getReviewnum();
    }

    @Override
    public Long remove(Long num) {
        reviewRepository.deleteById(num);
        return num;
    }
}

