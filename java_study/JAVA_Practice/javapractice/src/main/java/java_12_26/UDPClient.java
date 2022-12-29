package java_12_26;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(System.in);
            while(true){
                System.out.print("보낼 메시지ㅣ");
                String msg =sc.nextLine();

                //보낼곳의 Ip생성
                InetAddress addr = InetAddress.getByName("192.168.0.156");

                //socket생성
                DatagramSocket ds = new DatagramSocket();

                //보낼 패킷생성
                DatagramPacket dp = new DatagramPacket(msg.getBytes(),msg.getBytes().length,addr,7777);
                ds.send(dp);
                ds.close();
            }

        }catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
