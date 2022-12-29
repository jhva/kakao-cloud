package java_12_26;

public class Lambda {
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println("람다식");

                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
