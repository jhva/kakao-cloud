package java_12_23;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChacterStreamMain {
    public static void main(String[] args) {
        //오늘 날짜로 파일명 생성 : 2022-12-23.log
        String directory = "C:\\Users\\user\\Documents\\log";

        //오늘 날짜 문자열만들기
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String filename = sdf.format(date);
        //파일경로만들기
        String path = String.format("%s%s%s", directory, filename, ".log");
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            pw.println("안녕하세요 ㅎㅇ");
            pw.println("으이 시");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }


        try(BufferedReader br =new BufferedReader(new FileReader(path))){
            while(true){
                String str =br.readLine();
                if(str == null){
                    break;
                }
                //숫자가 아닌 문자열을 숫자로 변환하면
                //NumberFormatException 발생
                //web에서 파라미ㅓ는 무조건 문자열
                //파라미터를 숫자로 변환하는 경우 발생할 수 있는 예오 ㅣ
                System.out.println(Integer.parseInt(str));
            }
        }catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
