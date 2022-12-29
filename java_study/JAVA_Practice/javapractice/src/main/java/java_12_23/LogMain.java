package java_12_23;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

public class LogMain {
    public static void main(String[] args) {
        //파일경로화깅ㄴ
        File file = new File("log.txt");
        System.out.println(file.exists());
        //문자열을 읽기 위한 스트램생성
        try (BufferedReader br = new BufferedReader(new FileReader("log.txt"))) {
            while (true) {
                String log = br.readLine();
                if (log == null) {

                    break;
                }
                String[] ar = log.split(" ");
                System.out.println(ar[ar.length - 1]);
                ;
                int sum=0;
                try{
                    sum= sum+Integer.parseInt(ar[args.length - 1]);

                }catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
//                System.out.println(log);
                System.out.println(sum);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
