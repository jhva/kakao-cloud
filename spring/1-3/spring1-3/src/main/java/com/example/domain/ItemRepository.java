package com.example.domain;

public class ItemRepository {
    //다른패키지에서는 인스턴스 생성을 못하도록 생성자의
    //접근 지정자를 package로 설정
    ItemRepository() {
    }

    public Item get() {
        Item item = null;

        item = new Item();
        item.setItemId(1);
        item.setItemName("예나");
        item.setDescription("오리");
        item.setPrice(3333);
        item.setPictureUrl("yena.jpg");

        return item;
    }
}
