package java_12_14;

import java.util.Arrays;

public class SelectArray {
    public static void main(String[] args) {
        int[] arr = {1, 5, 3, 2, 4};

        //선택 정렬
        //첫번째 부터 마지막 앞 자릭까지
        for (int i = 0; i < arr.length - 1; i++) {
            //기준의 뒤부터 마지막 자리까지
            for (int j = i + 1; j < arr.length; j++) {
                //자신의 데이터를 비교해서 기준 자리의 데이터가 크면
                //2개의 데이터를 swap
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    System.out.println(temp + "temp");
                    arr[i] = arr[j];

                    arr[j] = temp;
                    System.out.println(arr[j]);
                }
            }
            System.out.println(Arrays.toString(arr));
        }

    }
}
