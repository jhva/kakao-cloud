package com.example.springboardproject;


import com.example.springboardproject.BaseEntity;
import com.example.springboardproject.Board;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "board")
//entity는 세터를 안넣는다
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rno;
    private String text;

    private String replyer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;
}
