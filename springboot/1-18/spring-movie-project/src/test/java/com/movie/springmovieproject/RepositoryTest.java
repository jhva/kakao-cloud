package com.movie.springmovieproject;


import com.movie.springmovieproject.domain.Member;
import com.movie.springmovieproject.domain.Movie;
import com.movie.springmovieproject.domain.MovieImage;
import com.movie.springmovieproject.domain.Review;
import com.movie.springmovieproject.repository.MeberRespository;
import com.movie.springmovieproject.repository.MovieImageRepository;
import com.movie.springmovieproject.repository.MovieRepository;
import com.movie.springmovieproject.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.util.Arrays;
import java.util.List;
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

    @Test
    public void detailTest() {
        List<Object[]> list = movieRepository.getMovieWithAll(3L);
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void getReviews() {
        Movie movie = Movie.builder().mno(4L).build();
        System.out.println(reviewRepository.findByMovie(movie)
        );

        //여기서 review.getRivew나 뭐 가져오게되면 에러가발생한다 그이유는
        //lazy로 해놓았기때문이다
        //이럴땐 @Transaction을 사용하자
        //EntityGraph를 적용하여 @Transaction을 사용안해도됨 .
    }


    @Test
    @Transactional
    @Commit
    public void deleteByMemegber() {
        Member memeber = Member.builder().mid(83L).build();
        System.out.println(memeber);
        reviewRepository.deleteByMember(memeber);
    }

    @Test
    @Transactional
    @Commit
    public void update(){
        reviewRepository.updateByMember(50L);
    }

}
