package java_12_22;

public class ThreadMain {
    public static void main(String[] args) {
        Thread th1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(getName());
                    } catch (Exception e) {
                    }
                }
            }

        };
        th1.setDaemon(true);
        th1.start();
        String str = null;
        //nullpointException ㅂ라생
        // Daemon이 아닌 스레드는 다른 스레드가 중지되더라도 계속 동작
        System.out.println(str.trim());

    }


}
