package java_12_22;

public class ThreadAndProcess {
    public static void main(String[] args) {
        new Thread(){
            public void run(){
                for(int i=0; i<10; i++){
                    try{
                        Thread.sleep(1000);
                        System.out.println(i);
                    }catch (Exception e){

                    }
                }
            }
        }.run();
    }
}
