package java_12_21;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    //quicksort 를 위한 메서드
    // left 는 비교의 시작 위치
    //right 는 비교의 반대편 끝 위치
    //data 가 정렬할 배열
    public static void quickSort(int left,int right,int [] data){
        //데이터 출력
        System.out.println(Arrays.toString(data));
        //기준점 선정 - 맨왼쪽을 기준으로 설정
        int pivot = left;
        //큰 데이터를 찾기 위한 인덱스
        int i = left +1;
        //작은 데이터를 찾기 위한 index -> right
        //나중에 데이터를 교체해야하기 때문에 pivot의 위치를 저장
        int j = pivot;

        //배열의 데이터가 2개이 상인 경우만 수행
        // 배열의 데이터가 1개이면 left 와 right 가 같아지눼
        if ( left< right ){
            for(; i<= right; i=i+1){
                if(data[i] < data[pivot]){
                    j =j +1;
                    int  temp = data[j];
                    data[j]=data[i];
                    data[i]=temp;
                }
            }
            //pivot 위치의 데이터를 자신의 위치로 이둉 =
            int temp = data[left];
            data[left] =data[j];
            data[j]=temp;
            //pivot 의 위치를 비교가 끝ㅌ난 자리로 수정
            pivot = j;
            //pivot 의 왼쪽 부분을 재귀적으로 다시 quick sort
            quickSort(left , pivot-1, data);

            //pivot의 오른족 부분을 재귀적으로 다시 quick sort
            quickSort(pivot+1,right, data);
        }
    }
    public static void main(String[] args) {
        int [] arr = {123,134,33,3,125,7,54,22};
        quickSort(0, arr.length-1,arr);
        System.out.println("오름 차순 정렬 후 ");
        System.out.println(Arrays.toString(arr));
//        String [] names = {"눈오는게제일싫어","하늘에서 뭐가 내리면 너무싫어","칼로리"};
//        //원본은 항상 냅두고 복사본가지고 하는게좋다.
//        String[] copy = Arrays.copyOf(names,names.length);
//        Arrays.sort(copy);
//        System.out.println(Arrays.toString(copy));
//        Arrays.sort(copy, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
//            }
//        });
    }
}
