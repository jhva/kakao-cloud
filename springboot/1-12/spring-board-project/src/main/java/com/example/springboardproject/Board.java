package com.example.springboardproject;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")//entity는 세터를 안넣는다
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bno;
    private String title;
    private String content;


    @ManyToOne(fetch = FetchType.LAZY)
    //처음에는 가져오지 않고 사용을 할 떄 가져온다
    private Member writer;


    //title을 수정하는 메서드
    public void changeTitle(String title) {
        if (title == null || title.trim().length() == 0) {
            this.title = "무제";
            return;
        }
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
