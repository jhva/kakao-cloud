package java_12_20;

import java.io.UnsupportedEncodingException;

//이런형태로 만들면 Gender Type의 변수에는 MAN아니면 WOMAN만 가능하다
enum Gender {
    MAN,WOOMAN,
}
public class Main {
    public static void main(String[] args) {
        String [] nicknames = {"adam","군계","파이터"};
        Data original = new Data(1,"itstudy",nicknames);
        Data data =original;
        System.out.println(original);
        //참조를 복사한 경우는 data의 변경이 곧 original 의 변경이 된다
        data.setNum(2);

//        Double d = new Double(13.64);
//        d=null;
//        System.out.println(d);
//
//        //기본형 데이터 로 변환
//        double x=d;
//        System.out.println(x);

        //자바 복제를 이용하고자 하는 경우 clone 메서드를
        //재정의 해야한다
        //재정의 할 때 Cloneable 인터페이스를 implements 하기를 권장

//        String str ="Hello";
//        System.out.println(System.identityHashCode(str));
//        //string 은데이터 수정이 안되기 때문에
//        //새로운 공간에 기존의 문자열을 복사한 후 작업을 수행하고
//        // 그 참조를 다시리턴
//        //기존의 데이터가 저장된 공간은 메모리 낭비가 될수있다
//        str+="java";
//        System.out.println(System.identityHashCode(str));
//
//        //변경가능한 문자열을 저장
//        StringBuffer sb = new StringBuffer("Hello");
//        System.out.println(System.identityHashCode(sb));
//        //문자열을 추가하면 현재 저장된 공간에 이어붙이기를 수행
//        sb.append("kava");
//        System.out.println(System.identityHashCode(sb));

        double lat = 12.789;
        double lng = 24.2987;
        //위의 데이터를 가지고 문자열로 생성
        String msg= String.format("위되:%.3f 경도 :%.3f\n", lat, lng);
        System.out.println(msg);

        String incodingStr= "안녕하세요";
        //바이트 배열로 무ㅠㄴ자열을 리턴
        try{
            //바이트 배열로 문자열을 변환 - MS949
            //동일한 프로그램이 아닌 시스템 과 채팅을 할 때는
            //문자열을 직접 전송하지 않고
            //바이트  배열을 만들어서전송
            byte [] bytes = incodingStr.getBytes("MS949");
            //Byte 배열을 문자열로 변환
            String result = new String(bytes,"MS949");
            //문자열이 깨지면 인코딩 방식을 ㅎ확인하고 변경해야한다.
            System.out.println(result);

        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }

        String os =System.getProperty("os.name");
        String version =System.getProperty("java.version");
        System.out.println(os);
        System.out.println(version);

        long start = System.currentTimeMillis();
//
//        for(int i =0; i<100000000; i=i+1){
//            long end = System.currentTimeMillis();
//            System.out.println((end-start)+"밀리초");
//        }

        Runtime r1 = Runtime.getRuntime();
        Runtime r2 = Runtime.getRuntime();
        System.out.println(System.identityHashCode(r1));
        System.out.println(System.identityHashCode(r2));

        //프로세스 실행
        //mac 은 open 파일경로 형태로 입력해야함
        //generic 적용된 클래스의 인스턴스를 만들때는 
        //실제 자료형을 결정지어야 경고가 발생하지않는다
        GenericClass<String>obj1 = new GenericClass<>("아..졸려","2시부터4시지옥","힘드라");

        obj1.display();


        //옵션을 사용하기 위해서 예전에는 final 상수를 이용했다
        final int Man = 0;
        final int WOMAN = 1;

        //int 로 만들면 이렇게 정의하지 않은 값을 대입하는 게 가능
        Gender gender = Gender.WOOMAN;
        System.out.println(gender);

    }
}
