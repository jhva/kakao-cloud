package java_12_27;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParallelMain {
    public static void main(String[] args) {
        //정수 리슽르 생성
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);


        long start = System.currentTimeMillis();
        //일반 스트림으로 1초씩 쉬면서 출력
        list.stream().forEach(imsi -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("걸린시간" + (end - start));


        //스트림에서 병렬처리하기

        start = System.currentTimeMillis();
        //일반 스트림으로 1초씩 쉬면서 출력
        list.stream().parallel().forEach(imsi -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
                e.printStackTrace();
            }
        });
        end = System.currentTimeMillis();
        System.out.println("걸린시간" + (end - start));
    }
}
