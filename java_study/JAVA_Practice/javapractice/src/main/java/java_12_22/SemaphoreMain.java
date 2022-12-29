package java_12_22;


import java.util.concurrent.Semaphore;

class SemaphoreThread implements Runnable {
    private Semaphore semaphore;
    private String message;

    public SemaphoreThread(Semaphore semaphore, String message) {
        this.semaphore = semaphore;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            //공유 자원을 사용하기 전에 호출
            semaphore.acquire();
            System.out.println(message);
            Thread.sleep(10000);
        } catch (Exception e) {
        }
        semaphore.release();//공유자원해제

    }
}

public class SemaphoreMain {
    public static void main(String[] args) {
        //동시에 실행될 수 있는 스레드의 개수를 설정하는ㅇ 클래스
        //동시에 여럭 ㅐ의 스레드가 수행될 상황에서
        //성능 저하를 막기위해서
        Semaphore semaphore = new Semaphore(3);
        new Thread(new SemaphoreThread(semaphore, "1")).start();
        new Thread(new SemaphoreThread(semaphore, "2")).start();
        new Thread(new SemaphoreThread(semaphore, "3")).start();
        new Thread(new SemaphoreThread(semaphore, "4")).start();
    }
}
