package java_12_26;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownload {
    public static void main(String[] args) {
        new Thread(){
            public void run(){
                try{
                    String addr= "https://www.google.com/imgres?imgurl=https%3A%2F%2Fimg.etoday.co.kr%2Fpto_db%2F2022%2F11%2F600%2F20221108175829_1816470_1200_1800.jpg&imgrefurl=https%3A%2F%2Fwww.etoday.co.kr%2Fnews%2Fview%2F2190409&tbnid=Ze-_-YHfvk_khM&vet=12ahUKEwj3yNrwpZb8AhUqCqYKHQ9pAqQQMygNegUIARCnAQ..i&docid=8zujYjuCvpjUQM&w=600&h=900&q=%EC%9E%A5%EC%9B%90%EC%98%81&ved=2ahUKEwj3yNrwpZb8AhUqCqYKHQ9pAqQQMygNegUIARCnAQ.jpg";
                        //확장자추출
                    // .은 \와 함께 기재해야한다 .
                    String [] ar= addr.split("\\.");
                    String ext =ar[ar.length-1];
                    System.out.println(ext);


                    URL url = new URL(addr);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setUseCaches(false);
                    con.setConnectTimeout(30000);


                    InputStream in =con.getInputStream();
                    FileOutputStream fos = new FileOutputStream("원영앙."+ext);
                    while (true) {
                        //데이터를 저장할 바이트 배열 저장
                        byte [] raster= new byte[512];
                        //바이트 배열에 읽어서저장
                        int len =in.read(raster);
                        if(len<=0){
                            break;
                        }
                        //읽은 내용이 있으면 파일에 기록
                        fos.write(raster,0,len);
                        in.close();
                        fos.close();
                        con.disconnect();
                    }
                }catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
        }.start();
    }
}
