package com.movie.springmovieproject.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
//serializable (직렬화) : 데이터를 전송할 대 객체 단위로 전송할 수 있도록해주는 인터페이스
public class UpdateResultDTO implements Serializable {

    private String uploadPath;
    private String fileName;
    private String uuid;


    //실제 이밎 경로를 리턴해주는 메서드
    public String getImageURl() {
        try {
            return URLEncoder.encode(uploadPath + "/" + uuid + fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return "";
    }
}
