package java_12_13;

public class primitive {
    public static void main(String[] args) {
//        int x = 1000;
//        System.out.println("x" + x);
//        //동일한 공간에서 동일한 이름의 변수를 2번 생성하면 에러
//        //int x = y;
//        System.out.println("X" + System.identityHashCode(x));
//
//        char ch = '0';
//        System.out.println();
//        int n1 = 20;
//        int n2 = -20;
//
//        System.out.println("n1:" + Integer.toBinaryString(n1));
//        System.out.println("n1:" + Integer.toBinaryString(n2));


        short s1 = 20;
        short s2 = 30;
        //short 사이의 덧셈이고 결과도 50이라서 short 저장가능하지만 이문장은에러이다
//자바의 산술연산의 최소 단위는 int
        //자바는 s1과 s2로 int로 변환해서 연산을 수행하므로
        //결과는 int가 되서 short 에 저장할 수 없다.
        short result = (short) (s1 + s2);
        System.out.println(result);

    }
}
