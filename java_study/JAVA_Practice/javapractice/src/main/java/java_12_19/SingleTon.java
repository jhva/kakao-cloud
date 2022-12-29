package java_12_19;

public class SingleTon {
//    private Singleton() {
//    }

    //하나의 인스턴스 참조를 저장하기 위한 속성을 생성
    private static SingleTon singleton;

    // 인스턴스의 참조를 리턴하는 메서드
    public static SingleTon sharedinstance() {
        if (singleton == null) {
            singleton = new SingleTon();
        }
        return singleton;
    }
}
