package com.example.springbootpractice;


public class mainTestt {
    public static void main(String[] args) {
        Book book = new Book("책제목", "책내용");


        System.out.println(book.getTitle() + "get으로가져온 book");
        System.out.println(book.getTitle() + "get으로가져온 컨텐트");
        System.out.println("처음 book"+ book.toString());

        //근데 나 book 제목을 바꾸고싶다 setter
        book.setContennt("책내용바뀌었습니다");
        book.setTitle("책제목바뀌었ㅅ브니다");

        //이러면 바뀌게됨 왜냐 ? 인스턴스 참조의 주소값에 변경이되었기때문에
        System.out.println("바뀐 book"+ book.toString());

    }
}
