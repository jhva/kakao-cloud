package com.movie.springmovieproject.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = {"movie", "member"})
//@Table(name="m")
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewnum;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private int score;
    private String text;

    public void chageGrade(int grade) {
        this.score = grade;
    }

    public void chageText(String text) {
        this.text = text;
    }
}
