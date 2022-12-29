package java_12_15;

public class Student {
    public static String psStaticVariable;

    static {
        System.out.println("로고 출력");
        psStaticVariable = "";
    }

    public int num;
    public String name;
    public int kor;
    public int eng;
    public int mat;
}
