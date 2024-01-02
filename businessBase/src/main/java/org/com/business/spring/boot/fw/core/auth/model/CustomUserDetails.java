package org.com.business.spring.boot.fw.core.auth.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * 認証ユーザーの情報を格納するクラス
 */
public class CustomUserDetails extends User {

    /** ユーザ名 */
    private String name;

    /**
     * @param username
     * @param password
     * @param authorities
     */
    public CustomUserDetails(String username, String password,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name セットする name
     */
    public void setName(String name) {
        this.name = name;
    }

}