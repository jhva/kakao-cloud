package com.example.springboardproject;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_member")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
//entity는 세터를 안넣는다
public class Member extends BaseEntity {

    @Id
    private String email;
    private String password;
    private String name;
}
