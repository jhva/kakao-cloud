package java_12_19;

//포함되는 클래스
public class Embeddedclass {
    public int score;
    private Embed embed;

    //생성자를 이용한 주입
    //다른곳에서 받아오고싶을때
    //만들때 생성이되는ㄴ거 메모리 효율은 떨어질수 있음
    public Embeddedclass(Embed embed) {
        this.embed = embed;
    }

    public void set() {
        //여기서 포함하는 클래스의 멤버를 수정
        embed.name = "독수리 다방";
        System.out.println(embed.name);
    }

    //주입을 위한 setter
    //필요할 때 호출 하는것
    public void setEmbed(Embed embed) {
        this.embed = embed;
    }

}
