package java_12_14;

import java.util.Arrays;

public class ArrayClone {
    public static void main(String[] args) {
        String[] arr = {"라이언", "복숭아", "솜사탕"};
        //위의 배열의 내용을 가지고 데이터를 1개 추가한 배열을 생성
        String[] arr1 = new String[arr.length + 1]; //배열을 추가하고싶은 개수만큼 만들기

        //베열 요소 복제


        //직접 구현 예시
//        for (int i = 0; i < arr.length; i++) {
//            arr1[i] = arr[i];
//        }
        String[] copyArr = Arrays.copyOf(arr, arr.length + 1);
        copyArr[3] = "아 집가고싶어";

        System.out.println(Arrays.toString(copyArr));
    }
}
