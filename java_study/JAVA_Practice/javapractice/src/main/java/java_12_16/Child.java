package java_12_16;

public class Child extends Parent {

    String name;

    Child() {
    }

    ;

    Child(int age, String name) {
        super(age);
        this.name = name;
    }

    @Override
    void prinnf() {
        System.out.println("child override printf");
    }
}
