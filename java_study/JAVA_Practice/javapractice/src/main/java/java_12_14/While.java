package java_12_14;

public class While {
    public static void main(String[] args) {
        int cnt = 5;
        int tot = 0;
        do {
            //반복할 내용
            tot += cnt;
            cnt += 1;
        }
        while (cnt <= 10);
        System.out.println(cnt);
        System.out.println(tot);


    }
}
