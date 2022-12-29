package java_12_21;

import java.util.*;

public class LottoMain {
    public static void main(String[] args) {
        //로또 번호생성기
        //1-45 까지의 숫자 6개를 입력받앗 ㅓ젖아한 후 출력
        //입력받을 때 1~45 사이의 숫자가 아니면 다시입력하도록
//      6개의 정수 배열 생성
//        Scanner sc = new Scanner(System.in);
//        int[] lotto = new int[6];
//        int len = lotto.length;
//        for (int i = 0; i < len; i++) {
//            try {
//                System.out.println("로또 번호 입력");
//                lotto[i] = sc.nextInt();
//                if (lotto[i] < 1 || lotto[i] > 45) {
//                    System.out.println("1 -45 사이만 입력하셈");
//                    i--;
//                    //중복 검사를 하지않고 패스하기 위해서=
//                    continue;
//                }
//                //데이터 중복검사
//                // 첫번째 부터 현재 데이터 앞까지 비교
//                boolean falg = false;
//                int j = 0;
//                for (; j < i; j++) {
//                    //2개의 데이터가 같을때
//                    //중복
//                    if (lotto[i] == lotto[j]) {
//                        falg = true;
//                        break;
//                    }
//                }
//                if(falg == true){
//                    System.out.println("중복된 숫자 입니다 ");
//                    i--;
//                }
//                Arrays.sort(lotto);
//            } catch (Exception e) {
//                i--;
//                sc.nextLine();
//                System.out.println("숫자 넣으세요 ");
//
//            }
//        }
        //중복된 데이터를 저장하지 않고 데이터를 정렬해서 저저장하는
        //자료구조 클래스
        //Tres set

        Scanner sc = new Scanner(System.in);
        Set<Integer> set = new TreeSet<>();
        //set에 6개의 데이터가 저장되지 않은 경우
        while(set.size()<6){
            System.out.println("로또 번호를 입력해주세요 ");
            int temp = sc.nextInt();
            if(temp< 1 || temp >45){
                System.out.println("로또 번호를 1~45 까지 의 숫자를 입력해주세요");
                continue;
            }

            boolean result = set.add(temp);
            if(result==false){
                System.out.println("중복된 로또번호입니다");
            }
        }
        System.out.println(set);
        sc.close();


    }
}
