package java_12_16;

public class Main {
    public static void main(String[] args) {
        Child c = new Child();
        Parent p1 = new Parent();
        Parent p = (Parent) c;
        p.prinnf();

        Child c2 = (Child) p;
        c2.prinnf();


    }
}
