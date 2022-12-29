package java_12_15;

public class MethodClass {
    private static int num;
    private String name;

    public static void method1() {
        num = 1;
        //static 메서드에서는 instance 속성이안됨
        //이유가 this가 없기때문에
//        name ="adam";
        System.out.println("MethodClass method1");
    }

    public void method2() {
        System.out.println("instance method");
    }


    public static void noArgsMethod() {
        for (int i = 0; i < 5; i++) {
            System.out.println("method");
        }
    }

    public static void twoArgsMethod(int n, String msg) {
        for (int i = 0; i < n; i++) {
            System.out.println("twoArgsMethod");
        }
    }

    //매개변수가 정수 1개인 멧드
    //methodClass.onArgsMetod(
    public static void onArgsMethod(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("onArgsMethod");
        }
    }

    //리턴이 없는 메서드는 연속해서 호추랗는 것이 불가능
//    public void addWithInteger(int firtst, int second) {
//        System.out.println("결과" + (firtst + second));
//    }

    public int addWithInteger(int firtst, int second) {
        return (firtst + second);
    }

    public void display() {
    }

    public void display(int a) {
    }

    //내부에서 매개변수의 값을 수정해도 넘겨준 데이터는 변경되지않는다
    public static void callByValue(int n) {
        n = n + 1;
        System.out.println("n callByValue" + n);
    }

    //매개변수가 참조형인경우
    public static void callByReference(int[] ar) {
        ar[0] = ar[0] + 1;
        System.out.println("n callByReference" + ar[0]);
    }

    public int scroe = 100;

    public void thisMethod() {
        int score = 200;
        System.out.println("score" + this.scroe);
    }

    //매개변수의 개수를 가변으로 설정
    public static void display(String... args) {
        for (String temp : args) {
            System.out.println(temp);
        }
    }
}
