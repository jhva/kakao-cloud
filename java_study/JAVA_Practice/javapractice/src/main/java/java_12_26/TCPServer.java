package java_12_26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        try{
            ServerSocket ss = new ServerSocket(8080);
            while(true){
                System.out.println("서버 대기중");

                //대기
                Socket socket = ss.accept();
                //접속한 클라ㅣㅇ언트 정보 확인

                //클라이언트가 전송한 내용읽기
                BufferedReader br  =new BufferedReader(new InputStreamReader(socket.getInputStream()));

                //한줄의 메세지 일ㄹ긱
                String msg = br.readLine();
                System.out.println(msg);

                br.close();
                socket.close();
            }
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }
}
