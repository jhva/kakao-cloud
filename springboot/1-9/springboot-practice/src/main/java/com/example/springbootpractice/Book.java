package com.example.springbootpractice;

public class Book {

    private String title;
    private String contennt;


    //내가말한 초기값
    public Book(String title, String contennt) {
        this.title = title;
        this.contennt = contennt;

    }

    public void setTitle(String title) {

        this.title = title;
    }

    public void setContennt(String contennt) {
        this.contennt = contennt;
    }

    public String getTitle() {
        return title;
    }

    public String getContennt() {
        return contennt;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", contennt='" + contennt + '\'' +
                '}';
    }
}
