package java_12_14;

public class Array {
    public static void main(String[] args) {
        String[] socker = {"반바스텐", "굴리트", "박지성"};

        //배열의 데이터 순회
        int len = socker.length;
        for (int i = 0; i < len; i += 1) {
            String str = socker[i];
            System.out.println(str);
        }

        int[] ar = new int[3]; //숫자는 0으로 초기화
        ar[0] = 20;
        ar[1] = 33;

        //배열의 순회
        for (int temp : ar) {
            System.out.println(temp);
        }
    }
}

