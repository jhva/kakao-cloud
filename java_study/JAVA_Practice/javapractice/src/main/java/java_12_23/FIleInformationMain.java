package java_12_23;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FIleInformationMain {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\user\\Documents\\kakaoCloudSchool");
        if (file.exists()) {
            System.out.println("파일의 크기는" + file.length());
            System.out.println("마지막 수정 날짜" + file.lastModified());

            Date date = new Date(file.lastModified());
            System.out.println("마지막 수정 날짜" + date);
            String pattern = "yyyy/mm/dd";
            SimpleDateFormat format = new SimpleDateFormat(pattern);

            System.out.println(format.format(date).toString());

        } else {
            System.out.println("파일이 존재하지 않는다");
        }

    }
}
