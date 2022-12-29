package java_12_20;

import java.util.Arrays;
import java.util.Objects;

public class Data implements Cloneable {
    private int num;
    private String name;
    private String [] nickname;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getNickname() {
        return nickname;
    }

    public void setNickname(String[] nickname) {
        this.nickname = nickname;
    }

    public Data(int num, String name, String[] nickname) {
        //속성을 대입받아서 생성하는 생성자
        // 인스턴스를 빠르게 초기화하기 위해서 사용
        this.num = num;
        this.name = name;
        this.nickname = nickname;
    }
//인스턴스의 내ㅑ용을 빠르게 만들기 위해서사용
    //디버깅을 위한메서드
    @Override
    public String toString() {
        return "Data{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", nickname=" + Arrays.toString(nickname) +
                '}';
    }

    //복제를 위한 메서드
//    public Data clone(){
////        Data data = new Data();
//        data.setNum(this.num);
//        data.setName(this.name);
//        data.setNickname(this.nickname);
//        //요런방식을 얕은복사라한다
//        return data;
//
//    }


    //배열을 복제
//    String[]nicknames = Arrays

}
