package java_12_19;

public class Main {
    public static void main(String[] args) {
        //외부 클래스으 인스턴스를 생성해야함
//        InstanceInner instanceInner = new InstanceInner();

        //인스턴스 생성
        // 인터페이스 나 추상 클래스 등을 상속해서 클래스를 만들고
        //인스턴스를 만드는 경우
        //변수는 대부분 인터페이스 나 추상 클래스
        //생성자는 사용하고 하는 클래스의 생성자를 이용하는 경우가 많다.

        //상위 클래스 나 인터페이스로 만들어진 변수에
        //하위 클래스의 인스턴스  참조를 대입할 수 있다 .

        //이렇게 만들어진 변수는 상위 클래스 나 인터페이스에 존재하는 이름만 호출이가능하다
        //실제 호출되는 것은 생성자를 따라간다 .
//        SampleAbleImpl instance = new SampleAbleImpl();
//        instance.method();
//
//        //내부 클래스의 인스턴스 생성
//
//
//        // Anonymous Class 사용
//        SampleAble annoymous = new SampleAble() {
//            @Override
//            public void method() {
//                System.out.println("!23");
//            }
//        };
//        System.out.println(annoymous);
//        annoymous.method();
//
//        //메서드를 1번만 호출할 거라면 변수를 생성하지 않고 생성
//        new SampleAbleImpl() {
//            @Override
//            public void method() {
//                System.out.println("씨발아");
//            }
//        }.method();
//
//        //디자인패턴을 적용
//        SingleTon singleTon1 = SingleTon.sharedinstance();
//        SingleTon singleTon2 = SingleTon.sharedinstance();
//
//        System.out.println(System.identityHashCode(singleTon1));
//        System.out.println(System.identityHashCode(singleTon2));

        Table row1 = new Table();

        System.out.println(row1.getNum());

        Table row2 = new Table();
        System.out.println(row2.getNum());

    }
}
