package com.movie.springmovieproject.repository;

import com.movie.springmovieproject.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeberRespository extends JpaRepository<Member, Long> {
}
