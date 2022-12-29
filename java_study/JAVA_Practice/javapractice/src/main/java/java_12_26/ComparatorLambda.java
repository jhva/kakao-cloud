package java_12_26;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorLambda {
    public static void main(String[] args) {
        String[] ar = {"야구", "배구", "세종대왕", "축구"};
//        Arrays.sort(ar, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                //내림차순
//                return o2.compareTo(o1);
//            }
//        });
//        System.out.println(Arrays.toString(ar));

        Arrays.sort(ar, ((o1, o2) ->
                o1.compareTo(o2)
        ));
        System.out.println(Arrays.toString(ar));

    }
}
