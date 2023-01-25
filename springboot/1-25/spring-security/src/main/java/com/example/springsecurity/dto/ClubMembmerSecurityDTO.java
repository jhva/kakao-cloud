package com.example.springsecurity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;


@Getter
@Setter
@ToString
public class ClubMembmerSecurityDTO extends User implements OAuth2User {
    private String mid;
    private String mpw;

    private String email;
    private String name;
    private boolean del;
    private boolean social;
    private Map<String, Object> socialMap;

    public ClubMembmerSecurityDTO(String username, String password, Collection<? extends GrantedAuthority> authorities, String email, String name, boolean del, boolean social) {
        super(username, password, authorities);
        this.mid = username;
        this.mpw = password;
        this.email = email;
        this.name = name;
        this.del = del;
        this.social = social;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.getSocialMap();
    }

    @Override
    public String getName() {
        return this.mid;
    }
}
