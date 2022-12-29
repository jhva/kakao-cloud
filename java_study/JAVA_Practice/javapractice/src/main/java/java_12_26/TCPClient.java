package java_12_26;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        try {
            InetAddress addr = InetAddress.getByName("192.168.0.156");
            Socket socket = new Socket(addr, 8080);

            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.print("아존나조렬 ");
            pw.flush();
//            pw.close();
//            socket.close();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
