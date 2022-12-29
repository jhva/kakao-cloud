package java_12_21;

//Data 클래스 VO

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class VO {
    private int num;
    private String name;

    public VO(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public VO() {
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "VO{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }
}

public class VoMapMain {
    public static void main(String[] args) {
        VO vo = new VO(1, "12");
        System.out.println(vo.getName());
        System.out.println(vo.getNum());

        Map<String,Object> map =new HashMap<String,Object>();
        map.put("name","daf");
        map.put("num",3);
        System.out.println(map);

        //map의 모든 키를 가져와서 출력
        Set <String> keys = map.keySet();
        for(String key :keys){
            System.out.println(map.get(key));
        }
    }
}
