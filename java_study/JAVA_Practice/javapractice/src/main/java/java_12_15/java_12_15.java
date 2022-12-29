package java_12_15;

public class java_12_15 {
    public static void main(String[] args) {
//        Student student = new Student();
//        student.num = 1;
//        student.kor = 32;
//        student.kor = 13;
//        student.name = "아이린";
//        student.mat = 40;
//        student.psStaticVariable = "123";
//
//        System.out.println(student.psStaticVariable);
//        Student student2 = new Student();

        //static 메서드는 클래스 이름으로 호출가능
//        MethodClass.method1();
//
//
//        MethodClass method = new MethodClass();
//        method.method2();

        MethodClass.noArgsMethod();
        MethodClass.onArgsMethod(3);
        MethodClass.twoArgsMethod(2, "씨발련");

        MethodClass methodClass = new MethodClass();
        int as = methodClass.addWithInteger(1, 2);
        System.out.println(as);

        as = methodClass.addWithInteger(40, 50);
        System.out.println(as);
        //매개변수가 2개인 메서드
        // 매개변수가 2개 이상인 경우는 순서대로 대입

        //
        int x = 10;
        MethodClass.callByValue(x);
        System.out.println("x" + x);


        //배열을 메서드에게 넘기면 배열의 내용일 변경될 수도있다
        //매서드의 리턴이 없는 경우라면 print메서드를 제외하고는 원보능ㄹ 변경해주는것이다
        int[] ar = {10, 20, 30};
        MethodClass.callByReference(ar);
        System.out.println("br" + ar[0]);


        MethodClass.display("123", "!23", "!#$31431413");

    }
}

