package java_12_27;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsvMain {
    public static void main(String[] args) {
        //text파일을 읽을땐 BufferedReader 스트림생성
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("java_study/JAVA_Practice/javapractice/CSVtest.csv")
        ))) {
//            System.out.println(br); 문제없음


            //첫줄은 데이턱가 아니마로 첫줄을 배제하기 위한 변수
            boolean flag = false;

            //파싱한 겨로가를 저장히기 위한 list
            List<Player> list = new ArrayList<>();


            //한줄씩읽어보기
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                if (flag == false) {
                    flag = true;
                    continue;
                }
//                System.out.println(line+"line 프린트 "); // success

                // csv에 , 단위로 분할해보기
                String[] ar = line.split(",");
                Player player = new Player();
                player.setName(ar[0]);
                player.setAge(Integer.parseInt(ar[1]));
                String birth = ar[2];

                //문자열을 Date 타입으로 변환해서 대입
                //데이터 파싱
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(birth);
                player.setDate(date);
                player.setEmail(ar[3]);
                player.setNickname(ar[4]);
                list.add(player);
            }
            StringBuilder sb = new StringBuilder();

            for (Player player : list) {
                sb.append(player + "\t");
            }
            System.out.println(sb);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
