package com.it.bean;

public class Business_type {
    private Integer id;
    private String name;

    public Business_type() {
    }

    public Business_type(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Business_type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
