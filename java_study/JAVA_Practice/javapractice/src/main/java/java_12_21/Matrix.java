package java_12_21;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Matrix {
    public static void main(String[] args) {
        String[] doro = {"배유나", "감정훈", "고유진"};
        String[] lg = {"고닥다리", "asdf"};
        //2차원 배열 생성
        String[][] volley = {doro, lg};
        //2차원 배열 순회

        for (String[] temp : volley) {
            for (String value : temp) {
                System.out.println(value);
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("name", "lg");
        map.put("team", doro);
        Map<String, Object> map2 = new HashMap<>();
        map.put("name", "lg");
        map.put("team", lg);

        Map<String, Object> map3 = new HashMap<>();
        map.put("name", "lg");
        map.put("team", "!@3");
        Map[] v = {map,map2,map3};
        for(Map amp3 :v){
            System.out.println(map.get("name")+"\t");

        }
        System.out.println();

    }
}
