package java_12_26;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class CollectionList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        int len =list.size();
        for(int i=0; i<len; i++){
            System.out.print(list.get(i)+"\t");
        }
        System.out.println();

        //이터레이터 이용
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next()+"\t");
        }
        System.out.println();

        for(String temp : list){
            System.out.println(temp);
        }

        //stream api 를 활용
        //내부 반복자를 이용하기 때문에 가장 빠름
        Stream<String> stream = list.stream();
        stream.forEach((temp)-> System.out.println(temp+ "\t"));
    }
}
