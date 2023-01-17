package com.movie.springmovieproject.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
/*
제외하는 이유는 지연생성이기 때문에
get을 하지 않은 상태에서 toString을 호출하면
nullPoisterException 이 발생하기때문이다
 */
@ToString(exclude = "movie") //movie빼고 라는 말이다 .toString할때
@Embeddable // 부모테이블을 만들때 이 속성의 값을 포함시켜 생성해주세요
/*
1대1 관계에서 많이사용되며
양쪽에 데이터를 모두 갖고싶을때
 */
public class MovieImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;

    private String uuid; // 파일 이름이 겹치지않기위해서

    private String imgName; //파일이름
    private String path;//하나의 디렉토리의 너무 많은 파일이 저장되지 않도록 업로드 한 날짜별로 파일을 기록하기 위한 디렉토리이름


    /*하나의 movie가 여러 개의 무비 이미지를 소유한다.
    데이터를 불러올때 movie를 불러오지 않고 사용할 때 불러온다
    외래키의 이름은 (안쓰게될시 외래키가 겹쳐진다) movie_mno
    해당 필드에대한 id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    //나여러개 movieimage one
    //반대는 oneToMany
    private Movie movie; //하나의 이미지는 여러
}
