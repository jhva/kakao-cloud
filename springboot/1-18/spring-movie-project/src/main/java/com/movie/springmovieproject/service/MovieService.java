package com.movie.springmovieproject.service;

import com.movie.springmovieproject.domain.Movie;
import com.movie.springmovieproject.domain.MovieImage;
import com.movie.springmovieproject.dto.MovieDTO;
import com.movie.springmovieproject.dto.MovieImageDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MovieService {
    //데이터 삽입을 위한 메서드
    Long register(MovieDTO movieDTO);

    //DTO를 ENtity로 변환
    //하나의 Entity가 아니라 Movie 와 MovieImage로 변환이 되어야해서
    //map으로 ㅣㄹ턴
    default Map<String, Object> dtoToEntity(MovieDTO movieDTO) {
        Map<String, Object> entityMap = new HashMap<>();
        Movie movie = Movie.builder()
                .mno(movieDTO.getMno())
                .title(movieDTO.getTitle())
                .build();
        entityMap.put("movie", movie);

        List<MovieImageDTO> imageDTOlist = movieDTO.getImageDTOLISt();
        if (imageDTOlist != null && imageDTOlist.size() > 0) {

            List<MovieImage> movieImageList = imageDTOlist.stream().map(dto -> {
                MovieImage movieImage = MovieImage.builder().path(dto.getPath())
                        .imgName(dto.getImgName())
                        .uuid(dto.getUuid())
                        .movie(movie)
                        .build();
                return movieImage;
            }).collect(Collectors.toList());
            entityMap.put("imgList", movieImageList);
        }
        return entityMap;
    }
}
