package java_12_22;


import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

//공유자원클래스
class Product {
    //공유자우넌
    List<Character> list;

    //공유지원을 넘겨받기 위한 생성자
    public Product(List<Character> list) {
        this.list = list;
    }

    //공유 자원에 데이터를 삽입하는 메서드
    synchronized public void put(char ch) {
        list.add(ch);
        System.out.println(ch + "입고");
        try {
            Thread.sleep(1000);

        } catch (Exception e) {
        }
        System.out.println("재고 수량" + list.size());
        notify();
    }

    //공유자원을소비하는 메서드
    synchronized public void get() {
        if (list.size() == 0) {
            try {
                //notify가 호출 될 때 까지 대기
                wait();
            } catch (Exception e) {

            }
        }
        //첫번재 데이터 꺼내기
        Character ch = list.remove(0);
        try {
            Thread.sleep(1000);
            System.out.println(ch + "출고");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("재고 수량" + list.size());

    }
}


//생산자 스레드
class Producer extends Thread {
    private Product product;

    public Producer(Product product) {
        this.product = product;
    }

    public void run() {
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            product.put(ch);
        }
    }

}

//소비자 스레드
class Customer extends Thread {
    private Product product;

    public Customer(Product product) {
        this.product = product;
    }

    public void run() {
        for (int i = 0; i < 26; i++) {
            product.get();
        }
    }
}

public class ProducerConsumerMain {
    public static void main(String[] args) {
        //공유 자원 생성
        List<Character> list = new ArrayList<>();

        Product product = new Product(list);

        //생산자 와 소비자 스레드 생성
        Producer producer = new Producer(product);
        Customer customer = new Customer(product);

        //thread start
        producer.start();
        customer.start();

    }
}