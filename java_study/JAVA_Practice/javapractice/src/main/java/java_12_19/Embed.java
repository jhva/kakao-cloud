package java_12_19;

public class Embed {
    public String name;

    public void createembedded() {
        //다른 클래스의 인스턴스를 생성
        //생성자를 이용한 주입 -생성자를 호출할 때 현재 인스턴스의 참조를 넘겨주는 것이다.

        Embeddedclass obj = new Embeddedclass(this);

        obj.setEmbed(this); //setter 이용

        //포함하는 인스턴스가 포홤된 인스턴스의 멤버 호출
        System.out.println(obj.score);
        obj.score = 124141;


        System.out.println(obj.score);
    }
}
