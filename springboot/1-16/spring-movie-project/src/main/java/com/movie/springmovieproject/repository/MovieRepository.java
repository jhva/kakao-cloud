package com.movie.springmovieproject.repository;

import com.movie.springmovieproject.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    //테이블 세개일때
    //coalesce 를 해주게 되면
    //있으면 r.score, 없으면 0을 쓴다
    //개수를 셀때는 distinct를 줘야한다
    @Query("select m,mi, avg(coalesce( r.score,0)),count(distinct r.reviewnum)" +
            " from Movie m" +
            " left outer join MovieImage mi on mi.movie=m" +
            " left outer join Review r on r.movie =m" +
            " group by m")

//    @Query()
    //페이지가 없다하면 보통 이런식이다
    //페이지가 있으면 Pageable이들어간다
    //Pageable이 매개변수로들어가면 리턴 타입이 page로바뀐다
    public Page<Object[]> getList(Pageable pageable);
}
