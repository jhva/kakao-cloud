package java_12_27;

import java.util.Date;

public class Player {
    private String name;
    private int age;
    private Date date;
    private String email;
    private String nickname;

    public Player() {
        super();
    }

    public Player(String name, int age, Date date, String email, String nickname) {
        super();
        this.name = name;
        this.age = age;
        this.date = date;
        this.email = email;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", date=" + date +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
