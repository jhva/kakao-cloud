package com.movie.springmovieproject.controller;


import com.movie.springmovieproject.dto.UpdateResultDTO;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class UploadController {
    @Value("${com.kjh.upload.path}")
    private String uploadpath;

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String filename) {
        ResponseEntity<byte[]> result = null;
        try {
            log.warn("파일이름" + filename);
            File file = new File(uploadpath + File.separator + URLDecoder.decode(filename, "UTF-8"));
            HttpHeaders header = new HttpHeaders();

            header.add("Content-Type", Files.probeContentType(file.toPath()));

            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            System.out.println(e.getLocalizedMessage());
        }
        return result;
    }


    //날짜 별로 디렉토리를 생성해주는 메서드
    private String makeFolder() {
        //오늘 날짜로 된 디렉토리 경로를 설정
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String realUploadPath = str.replace("//", File.separator);
        File uplaodPathDir = new File(uploadpath, realUploadPath);

        if (uplaodPathDir.exists() == false) {
            uplaodPathDir.mkdirs();
        }
        return realUploadPath;
    }

    @PostMapping("/uploadajax")
    public ResponseEntity<List<UpdateResultDTO>> uploadFile(MultipartFile[] uploadFiles) {
        //결과를 전달할 객체생성
        List<UpdateResultDTO> result = new ArrayList<>();

//        makeFolder();
        for (MultipartFile uploadFile : uploadFiles) {
            //이미지 파일이 ㅏㅇ니면 이미지 업로드 를 수행하지않는다
            if (uploadFile.getContentType().startsWith("image") == false) {
                log.warn("이미지파일아님");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
//실제 파일 이름 IE는 전체 경로가 들어오므로 마지막 부분만 추출
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
            log.info("fileName: " + fileName);

            //디렉토리 생성
            String realUplaodPath = makeFolder();
            //UUID 생성
            String uuid = UUID.randomUUID().toString();

            String SaveName = uploadpath
                    + File.separator +
                    realUplaodPath +
                    File.separator
                    + uuid
                    + fileName;
            //파일 업로드 성공
            Path savePath = Paths.get(SaveName);
            try {
                uploadFile.transferTo(savePath);
                result.add(new UpdateResultDTO(fileName, uuid, realUplaodPath));

            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
