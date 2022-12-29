package java_12_21;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Collection
{
    public static void main(String[] args) {
        //정수를 저장하기위한 ArrayList
        List<Integer> al = new ArrayList<>();

        //정수를 링크드리스트에 저장하기위한
        List<Integer> li =new LinkedList<>();


//        al.add(1);
//        li.add(3);
//
//        li.add(1);
//        li.add(3);
//
//        long start;
//        long end;
//        start= System.currentTimeMillis();
//        for(int i=0; i<100000; i++){
//           al.add(1,2);
//           li.add(1,3);
//        }
//        end = System.currentTimeMillis();
//        System.out.println("ArrayList 중간에추가하는시간 조회 시간"+ (end-start));
//
//        for(int i=0; i<100000; i++){
//            li.add(1,2);
//        }
//        end = System.currentTimeMillis();
//        System.out.println("linkedList 조회 시간"+ (end-start));

        List<String> lst = new ArrayList<String>();

        lst.add("김정훈");
        lst.add("나상천");
        lst.add("손흥민");

        lst.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println(lst);

    }
}
