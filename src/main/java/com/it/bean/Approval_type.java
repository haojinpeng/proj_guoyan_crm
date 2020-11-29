package com.it.bean;

public class Approval_type {
    private Long id;
    private String name;

    @Override
    public String toString() {
        return "Approval_type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Approval_type() {
    }

    public Approval_type(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
