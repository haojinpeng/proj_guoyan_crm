package com.it.bean;

public class User_Role {
    private int u_id;
    private String username;
    private int r_id;
    private String name;

    public User_Role() {
    }

    public User_Role(int u_id, String username, int r_id, String name) {
        this.u_id = u_id;
        this.username = username;
        this.r_id = r_id;
        this.name = name;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getR_id() {
        return r_id;
    }

    public void setR_id(int r_id) {
        this.r_id = r_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User_Role{" +
                "u_id=" + u_id +
                ", username='" + username + '\'' +
                ", r_id=" + r_id +
                ", name='" + name + '\'' +
                '}';
    }
}
