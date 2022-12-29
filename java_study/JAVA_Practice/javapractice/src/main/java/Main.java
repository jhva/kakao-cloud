import java_12_16.Member;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Member member = new Member();
        member.setAge(13);
        member.setEmail("junghun@asd.co.kr");
        member.setMarried(false);
        member.setPwd("123");
        member.setMarried(true);
        member.setBirthday(new Date());
//
        String[] nicknames = {"김정훈", "콬끼리"};
        member.setNicknames(nicknames);
//
//
        System.out.println(member);


        Member member1 = new Member("adsf", "1234", nicknames, new Date(), false, 23);

        System.out.println(member1);
    }
}