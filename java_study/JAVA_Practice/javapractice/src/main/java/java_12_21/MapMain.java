package java_12_21;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapMain {
    public static void main(String[] args) {
        Map<String,Object> map =new HashMap<>();
        map.put("name","adma");
        System.out.println(map.get("name"));
        map.put("name","군ㄱ");
        System.out.println(map.get("name"));
            //value를 object로 설정했을 때 사용
        //value 를 삽입할 때 삽이  ㅂ할 때 String 이지만
        //Map을 만들 때 Value ㄹ의 type을 Object 로 설정했기 때문에
        //get을 해서 데이터를 원상 보구하고자 하면 강제 형 변환을 해야한다.

        String value =(String)map.get("name");
        //모든 키를 가져오는것
        Set <String>keys = map.keySet();
        System.out.println(keys);
        System.out.println(value);
    }
}
