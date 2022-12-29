package java_12_22;


//thread 클래스로부터 상속받는 클래스
class ThreadEx extends  Thread{
    @Override
    public void run(){
        for (int i=0; i<10; i++){
            try{
                Thread.sleep(1000);
                System.out.println(i);
            }catch(Exception e){
                System.out.println(new Exception("히이이ㅇ기모링"));
            }
        }
    }
}
public class ThreadCreateMain {
    public static void main(String[] args) {
        Thread t = new ThreadEx();
        t.start();


        //annonymous 클래스 사용
        Runnable r= new Runnable() {
            @Override
            public void run() {
                for(int i= 0; i<10; i++){
                    try{
                        Thread.sleep(1000);
                        System.out.println(i);
                    }catch(Exception e){}
                }
            }
        };
        Thread t2 = new Thread(t);
        t2.start();

    }
}
