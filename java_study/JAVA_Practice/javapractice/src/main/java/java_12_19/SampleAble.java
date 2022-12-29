package java_12_19;


//인터페이스 안에 속성을 만들면 자동적으로 final 변수가 된다
//인터페이스 안에 메서드를 만들면 추상메서드가 된다 abstract
// default 라는 키워드를 추가해야한다
//인터페이스는 인스턴스 생성 못함
//클래스에 implements 해서 ㅏㅅ용
public interface SampleAble {
    //w자동으로 추상 메서드가 된다
    //추상 메서드는 하위 클래스에서 반드시 재정의
    public void method();
}
