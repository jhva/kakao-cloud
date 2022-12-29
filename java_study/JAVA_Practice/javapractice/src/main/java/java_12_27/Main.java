package java_12_27;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //Student 클래스의 list 생성
        List<StudentVO> list = new ArrayList<StudentVO>();

        list.add(new StudentVO(1, "개객", "남자", 23));
        list.add(new StudentVO(2, "바박2", "남자", 55));
        list.add(new StudentVO(3, "개3", "여자", 66));
        list.add(new StudentVO(4, "개4", "여자", 45));
        list.add(new StudentVO(5, "개5", "남자", 12));

        //score 의 합계
        //student 를 student.getScore 메서드의 결과를 이용해서 정수로 변환
        int sum = list.stream().mapToInt(StudentVO::getScore).sum();
        System.out.println("점수합계" + sum);


        //평균 구하기
        //Optional 붙는 자료형은 null 여부를 확인 후 사용
        OptionalDouble avg = list.stream().mapToInt(StudentVO::getScore).average();

        //값이 존재한다면

        if (avg.isPresent() == true) {
            System.out.println("평균 " + avg.getAsDouble());
        } else {
            System.out.println("평균을 구할 수 없음 ");
        }

        //reduce 집계
        //매개변수가 2개이고 리턴이있는 메서드를 제공

        // 처음 2개의 데이터를 가지고 메서드를 호출해서 결과를 만들고
        //그 다음부터는 결과 다음 데이터를 가지고 메서드 호출
        sum = list.stream().mapToInt(StudentVO::getScore)
                .reduce(0, (o1, o2) -> o1 + o2);

        System.out.println(sum + "reduce 집ㄱㅖ ");

        //남자만 추출해서 List 로 변환
        List<StudentVO> manList = list.stream().filter(student -> student.getGender().equals("남자"))
                .collect(Collectors.toList());

        System.out.println(manList);

        //그룹핑
        Map<String, Double> genderMap = list.stream()
                .collect(Collectors.groupingBy(StudentVO::getGender,
                        Collectors.averagingDouble(StudentVO::getScore)));
        System.out.println(genderMap.get("남자"));
    }
}