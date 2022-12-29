package java_12_26;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class IPMain {
    public static void main(String[] args) {
        try{
            //나의 아이피 가져오기
            InetAddress localAddress =InetAddress.getLocalHost();
            //네이버 아이피가져오기

            InetAddress [] naver = InetAddress.getAllByName("www.naver.com");
            System.out.println(Arrays.toString(naver));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
