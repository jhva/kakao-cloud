package java_12_16;

import java.util.Arrays;
import java.util.Date;

public class Member {

    private String email;
    private String pwd;
    private String[] nicknames;
    private Date birthday;
    private boolean married;
    private int age;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String[] getNicknames() {
        return nicknames;
    }

    public void setNicknames(String[] nicknames) {
        this.nicknames = nicknames;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNickname(int index) {
        return nicknames[index];
    }

    public void setNickname(int index, String ncickname) {
        nicknames[index] = ncickname;
    }

    ///매개변수가 없는생성자  -Default Constructor
    // 기본적으로 제공되지만 생성자를 만들면 없어진다
    public Member() {
        super();
    }

    public Member(String email, String pwd, String[] nicknames, Date birthday, boolean married, int age) {
        this.email = email;
        this.pwd = pwd;
        this.nicknames = nicknames;
        this.birthday = birthday;
        this.married = married;
        this.age = age;

    }

    public void init(
            String email,
            String pwd,
            String[] nicknames,
            Date birthday,
            int age,
            boolean married
    ) {
        //현재클래스에서찾고자할 댄 this
        //클래스의 메서드안에서 속성이름을 사용하면
        //메서드 안에 만들어진 것을 찾고 없으면 클래스에 만들어진
        //것을 그래도 없도없으면 상위클래스에서 찾고 없으면 에러
        this.pwd = pwd;
        this.nicknames = nicknames;
        this.birthday = birthday;
        this.age = age;
        this.married = married;
        this.email = email;


        //상위클래스에서 찾고싶을땐 super()

    }

    @Override
    public String toString() {
        return "Member{" +
                "email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", nicknames=" + Arrays.toString(nicknames) +
                ", birthday=" + birthday +
                ", married=" + married +
                ", age=" + age +
                '}';
    }


}
