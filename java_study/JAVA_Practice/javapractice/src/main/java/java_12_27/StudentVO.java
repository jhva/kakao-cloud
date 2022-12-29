package java_12_27;

public class StudentVO {
    private int num;
    private String name;
    private String gender;
    private String subject;
    private int score;

    @Override
    public String toString() {
        return "StudentVO{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", subject='" + subject + '\'' +
                ", score=" + score +
                '}';
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public StudentVO(int num, String name, String gender, int score) {
//        super();
        this.num = num;
        this.name = name;
        this.gender = gender;
        this.subject = subject;
        this.score = score;
    }
}
