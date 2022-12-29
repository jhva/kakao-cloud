package java_12_26;

import java.util.ArrayList;
import java.util.List;

public class MethodParameter {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        list.forEach(System.out::println);
    }
}
