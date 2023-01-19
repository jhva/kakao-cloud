package com.movie.springmovieproject.repository;

import com.movie.springmovieproject.domain.MovieImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
}

