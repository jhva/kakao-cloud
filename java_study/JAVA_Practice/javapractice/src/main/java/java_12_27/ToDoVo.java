package java_12_27;

public class ToDoVo {
    private Integer userId;
    private Integer id;
    private String title;
    private boolean completed;

    public ToDoVo(Integer userId, Integer id, String title, boolean completed) {
        super();
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public ToDoVo() {
        super();
    }

    @Override
    public String toString() {
        return "ToDoVo{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }

    public Integer getUserId() {

        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
