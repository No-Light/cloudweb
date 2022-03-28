package com.webcloud.entity;

public class AccountBean {
    private String username;
    private String password;
    private String kaptcha;

    public AccountBean(String username, String password, String kaptcha) {
        this.username = username;
        this.password = password;
        this.kaptcha = kaptcha;
    }

    public AccountBean() {
    }
}
