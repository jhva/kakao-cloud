package java_12_14;

import java.util.Scanner;

public class passif {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("아이디입력");
            String id = sc.nextLine();
            System.out.print("비밀번호입력");

            String password = sc.nextLine();
            if (id.equals("root") && password.equals("1234")) {
                //문자열은 생성 방법에 따라 다른인스턴스가 될 수 있어서 ㅓ
                //값이 동일한 지 비교할때 ==를 사용하면안되고
                //equals를 사용해야함
                System.out.println("로그인성공");
            } else {
                System.out.println("로그인실패 404");
            }
//
//            int score = sc.nextInt();
//            if(score >= 60){
//                System.out.println("함격");
//            }else{
//                System.out.println("불합격");
//            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
