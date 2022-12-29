package java_12_26;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ReductionOperator {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("123", "축구", "농구", "축구", "6768", "안철수", "안수리");

        Stream<String> stream = list.stream();
//        stream.forEach((tem)-> System.out.println(tem+"\t"));

        //2개 빼고 출력
//        stream.skip(2).limit(3).forEach((temp) -> System.out.println(temp+"\t"));


        //중복제거
//        stream.distinct().forEach((tem)-> System.out.println(tem+"\t"));

        //필터링
        // 매개변수가 1개이고  Boolean 을 리턴하는 함수를 대입
//
//        stream.distinct().filter(s -> s.charAt(0) == '축').
//                forEach((t) -> System.out.println(t + "\t"));

        //0으로 시작하는

//        stream.filter(s -> s.charAt(0) >= '아' && s.charAt(0) < "자").
//                forEach((t) -> System.out.println(t + "\t"));

    }
}
