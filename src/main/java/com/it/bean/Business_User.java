package com.it.bean;

public class Business_User {
    private Integer id;
    private String username;

    public Business_User() {
    }

    @Override
    public String toString() {
        return "Business_User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    public Business_User(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
