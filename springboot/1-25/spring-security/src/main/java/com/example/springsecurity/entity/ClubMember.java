package com.example.springsecurity.entity;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "roleSet")
public class ClubMember extends BaseEntity {


    @Id
    private String id;

    private String mpw;

    private String email;

    private String name;

    private boolean del;

    private boolean social;


    //권한 여러개의 권한을 소유

    @Builder.Default //널을 사용하면안된다 ?
    @ElementCollection(fetch = FetchType.LAZY) //set 이나 list배열을 넣을때 이렇게사용함
    //지연생성을 하게되면 toString을하게되면 에러가난다  exclude사용 하자
    //관계형데이터베이스에서는 리스트나 set을 사용할수없어서 테이블을 만들어준다
    private Set<ClubMemberRole> roleSet = new HashSet<>();


    public void changePassword(String mpw) {
        this.mpw = mpw;
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public void chageDel(boolean del) {
        this.del = del;
    }

    //권한 추가
    public void addRole(ClubMemberRole role) {
        this.roleSet.add(role);
    }

    //권한 삭제
    public void clearRoles() {
        this.roleSet.clear();
    }

    //소셜 변경
    public void socialChange(boolean social) {
        this.social = social;
    }

}
