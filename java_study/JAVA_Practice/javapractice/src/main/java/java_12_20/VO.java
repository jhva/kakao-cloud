package java_12_20;
//크기 비교가 가능한 메서드가 있다는것을 보장함
public class VO implements Comparable<VO> {


    private int num;
    private String name;
    private int age;

    public VO() {
//        super();
    }

    public VO(int num, String name, int age) {
        super();
        this.num = num;
        this.name = name;
        this.age = age;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "VO{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    //크기를 비교하는 메서드
    //양수를 리턴하면 순서가 변경이되고
    public int compareTo(VO o) {
        //숫자는 뺄셈을 해서 리턴하면 된다
        //순서를 변경하면 내림차순이 된다
//        return this.num - o.age;

        //문자는 뺄셈이안됨
        return this.name.compareTo(o.name);
    }
}
