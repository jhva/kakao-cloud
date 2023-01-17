package com.movie.springmovieproject.service;

import com.movie.springmovieproject.domain.Movie;
import com.movie.springmovieproject.domain.MovieImage;
import com.movie.springmovieproject.dto.MovieDTO;
import com.movie.springmovieproject.repository.MovieImageRepository;
import com.movie.springmovieproject.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Log4j2
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieImageRepository movieImageRepository;

    @Override
    @Transactional
    public Long register(MovieDTO movieDTO) {
        log.warn("movieDTO" + movieDTO);
        Map<String, Object> entityMap = dtoToEntity(movieDTO);
        Movie movie = (Movie) entityMap.get("movie");
        List<MovieImage> movieImageList =
                (List<MovieImage>) entityMap.get("imgList");
        movieRepository.save(movie);
        movieImageList.forEach(movieList -> {
            movieImageRepository.save(movieList);
        });
        return movie.getMno();
    }
}
