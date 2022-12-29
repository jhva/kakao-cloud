package java_12_20;


//
public class GenericClass<T> {
    //generic에 T라는 건없지만 class에서 선언할때 T가 만들어진것이다
    private T [] data;

    // ...은 데이터 개수에 상곤없이 매개변수로 ㅏㅂ다아서
    // 배열로 만들어주는 문법 - varagras
    public GenericClass(T ...n){
        data=n;
    }
    //배열의 데이터를 출력해주는 메서드
    public void display(){
        for(T temp :data){
            System.out.println(temp);
        }
    }
}
