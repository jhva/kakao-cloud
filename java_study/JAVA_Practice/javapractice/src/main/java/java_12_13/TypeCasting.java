package java_12_13;

public class TypeCasting {
    public static void main(String[] args) {
        double d = 37.4;
        //실수를 정수에 직접 대입하면 에러가 발생함
        // 실수가 정수보다 크기 때문이다
        int n = (int) d;

        //실수를 정수로 강제 형 변환하면 소수가 버려짐
        System.out.println("n" + n);

//        short s1 = 200;
//        short s2 = 300;
//        short cnt = (short) (s1 + s2);
//        //산술 연산은 int 로 변환되서 수행되므로 결과가 최소 int
//
//
//        //정수 2개의 평균을 실수로 구하고자 할 때
//        int scroe1 = 91;
//        int scroe2 = 90;
//        //정수를 가지고 산술 연산을 하면 결과는 정수
//        int avg = (scroe1 + scroe2) / 2;
//        System.out.println(avg + "avg");

        int x = 8;
        System.out.println(x << 2); //1번 밀때마다 2곱하기 32로 나눈 나머지만큼밀기
        System.out.println(x >> 2); //1번 밀때마다 2나누기
        System.out.println(x >>> 3); //부호변경

    }
}
