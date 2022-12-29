package java_12_23;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataMain {
    public static void main(String[] args) {
        //인스턴스 단위로 기록할 수있는 스트림 생성
        try(ObjectInputStream oos = new ObjectInputStream(new FileInputStream("sample.dat"))){
            //인스턴스 생성 로직
//            Data data = new Data(1,"adam","ㅅㅂ");
//            oos.writeObject(data);
//            oos.flush();

            //인스턴스 기록한거 보기
            Data data= (Data) oos.readObject();
            System.out.println(data);
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }
}
