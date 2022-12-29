package java_12_14;

public class ManyArray {
    public static void main(String[] args) {
        //2차원 배열
        String[][] abc = {{"야이씨발아", "예히"},
                {"아 여자..", " 여자 만나고싶어"},
                {"뒤통수이쁜여자", "기립근 이쁜여자"}};


        System.out.println(abc.length); //행의개수
        System.out.println(abc[0].length);//열의 개수

        String[] abc2 = {"여자", "못생긴여자", "남자"};
        for (int i = 0; i < abc2.length; i++) {
            System.out.println(abc2[i]);
        }
//        for (String[] ar : abc) {
//            for (String asd : abc) {
//                System.out.print(asd);
//            }
//            System.out.println();
//        }
    }
}
