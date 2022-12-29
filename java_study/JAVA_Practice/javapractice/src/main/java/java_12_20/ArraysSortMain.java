package java_12_20;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Comparator;

public class ArraysSortMain {
    public static void main(String[] args) {
        int [] ar= {60,30,29,524,10};

        //문자열 배열
        String [] br = {"아 졸려","자도자도 너무졸려","내일눈오는데 오기싫다"};
        System.out.println(ar);
        System.out.println(br);

        //정수 배열 정렬
        Arrays.sort(ar);
        System.out.println(Arrays.toString(ar));

        Arrays.sort(br);
        System.out.println();

        //vo 클래스 인스턴스 5개를 소유하는 배ㅑ열
        VO [] datas = new VO[5];
        datas[0] =new VO(1,"장애인",234);
        datas[1] =new VO(2,"김정훈",3);
        datas[2] =new VO(3,"김똥훈",11);
        datas[3] =new VO(4,"김진훈",1413);
        datas[4] =new VO(5,"김상훈",1343);


        System.out.println(Arrays.toString(datas));

    }

}
