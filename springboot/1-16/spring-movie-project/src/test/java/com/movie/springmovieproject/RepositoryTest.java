package com.movie.springmovieproject;


import com.movie.springmovieproject.domain.Member;
import com.movie.springmovieproject.domain.Movie;
import com.movie.springmovieproject.domain.MovieImage;
import com.movie.springmovieproject.domain.Review;
import com.movie.springmovieproject.repository.MeberRespository;
import com.movie.springmovieproject.repository.MovieImageRepository;
import com.movie.springmovieproject.repository.MovieRepository;
import com.movie.springmovieproject.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest

public class RepositoryTest {


    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository movieImageRepository;

    @Autowired
    private MeberRespository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;


    @Test
    public void isertMovie() {
        //임의로 데이터 넣어보기
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Movie movie = Movie.builder()
                    .title("무비제목" + i)
                    .build();

            movieRepository.save(movie);

            int count = (int) (Math.random() * 5) + 1;
            System.out.println(count);

            for (int j = 0; j < count; j++) {
                MovieImage movieImage =
                        MovieImage
                                .builder()
                                .uuid(UUID.randomUUID().toString())
                                .movie(movie)
                                .imgName("testImage" + j + ".jpg")
                                .build();

                movieImageRepository.save(movieImage);
            }
        });
    }

    @Test
    public void registerMember() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .nickname("nickname" + i)
                    .pw("11111")
                    .email("email" + i)
                    .build();
            memberRepository.save(member);
        });
    }

    @Test
    public void insertReview() {
        IntStream.rangeClosed(1, 200).forEach(i -> {
            //영화 번호
            Long mno = (long) (Math.random() * 100) + 1;
            //회원 번호
            Long mid = (long) (Math.random() * 100) + 1;

            Movie movie = Movie.builder()
                    .mno(mno)
                    .build();

            Member member = Member.builder().mid(mid).build();

            Review review = Review.builder()
                    .movie(movie)
                    .member(member)
                    .score((int) (Math.random() * 5) + 1)
                    .text("영화느낌.." + i).build();
            reviewRepository.save(review);
        });
    }

    @Test
    //조인 연습
    public void jointest() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"));

        Page<Object[]> reesult = movieRepository.getList(pageable);
        for (Object[] objects : reesult.getContent()) {
            System.out.println(Arrays.toString(objects));
        }
    }
}
