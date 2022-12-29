package java_12_23;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class ByteStreamMain {
    public static void main(String[] args) {
//        try {
//            FileOutputStream fos = new FileOutputStream("./sample.bin");
//            String msg ="123";
//            fos.write(msg.getBytes());
//            fos.flush();
//        } catch (Exception e) {
//            System.out.println(e.getLocalizedMessage());
//        }
//        try {
//            FileInputStream fis = new FileInputStream("./sample.bin");
//            ;
//            while (true) {
////                int x = fis.read();
////                if(x == -1){
////                    break;
////                }
//                byte[] b = new byte[fis.available()];
//                int len = fis.read(b);
//                if (len <= 0) {
//                    System.out.println("읽은 데이터 없음");
//                break;
//                }else{
//                    System.out.println(Arrays.toString(b));
//                }
//            }
        try (PrintStream ps = new PrintStream(new FileOutputStream("./sample.bin", true))) {
            //버퍼 단위로 기록
            String msg = "안녕";
            ps.write(msg.getBytes());
            ps.print(msg);
            ps.flush();

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
