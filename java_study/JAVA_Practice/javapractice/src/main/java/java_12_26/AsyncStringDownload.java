package java_12_26;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncStringDownload {
    public static void main(String[] args) {
        //스레드를 만들어서 당눌도ㅡ - 비동기처리
        new Thread(){
            //스레드를 만들어서 당눌도ㅡ - 비동기처리
            // @Override
            public void run(){
                //얘내는 뭊곤 예외처리를해야함
                try{
                    URL url = new URL("https://www.kakao.com");
                    HttpURLConnection con =(HttpURLConnection) url.openConnection();
                    con.setConnectTimeout(10000);
                    con.setUseCaches(false);
                    con.setRequestMethod("GET");

                    //읽기 위한 스트림 생성
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    StringBuilder sb= new StringBuilder();
                    while(true){
                        String line = br.readLine();
                        if(line == null){
                            break;
                        }
                        sb.append(line);
                    }
                    String html = sb.toString();
                    System.out.println(html);
                    br.close();
                    con.disconnect();
                }catch(Exception e){
                    System.out.println(e.getLocalizedMessage());
                }
            }
        }.start();
    }
}