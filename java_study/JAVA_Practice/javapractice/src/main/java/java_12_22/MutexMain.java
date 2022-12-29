package java_12_22;


//스레드 작업을 위한 클래스
class ShareData implements Runnable {
    private int result; //합계를 저장할 속성
    private int idx;//합계를 구할 때 사용할 인ㄷ젝스

    public int getResult() {
        return result;
    }

    //idx 의 값을 1씩 증가하면서 result에 더해줄메서드
    private synchronized void sum() {
        for (int i = 0; i < 1000; i++) {
            idx = idx + 1;
            try {
                Thread.sleep(1);
                System.out.println();
            } catch (Exception e) {
            }
            result = result + 1;
        }
    }

    @Override
    public void run() {
        sum();
    }
}

public class MutexMain {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        Thread th1 = new Thread(shareData);

        th1.start();
        Thread th2 = new Thread(shareData);

        th2.start();
        try{
            Thread.sleep(5000);
            System.out.println(shareData.getResult());
        }catch (Exception e) {}
        System.out.println(shareData.getResult());
    }
}
