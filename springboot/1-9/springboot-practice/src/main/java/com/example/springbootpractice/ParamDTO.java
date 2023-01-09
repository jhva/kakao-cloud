package com.example.springbootpractice;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ParamDTO {
    private String name;
    private String email;
    private String organization;
}
