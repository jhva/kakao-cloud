package java_12_26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class KaKaoSearch {
    public static void main(String[] args) {
//        URL url = new U
        //카카오도서검색api
        new Thread(new Runnable() {
            @Override
            public void run() {
                //url만들기

                try {
                    String apiurl = "https://dapi.kakao.com/v3/search/book?target=title";
                    apiurl += "&query=";
                    apiurl += URLEncoder.encode("노동청","utf-8");
                    URL url = new URL(apiurl);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setUseCaches(false);
                    con.setConnectTimeout(30000);
                    con.setRequestProperty(
                            "Authorization",
                            "KakaoAK b363b5afcbfbfcefd27f66bc97e322f6"
                    );
                    BufferedReader br =new BufferedReader(new InputStreamReader(con.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String imsi = br.readLine();
                        if (imsi == null) {
                            break;
                        }
                        sb.append(imsi+"\r\n");
                    }
                    String reuslt= sb.toString();
                    System.out.println(reuslt);
                    br.close();
                    con.disconnect();
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                    e.printStackTrace();
                }


            }
        }).start();
    }
}
