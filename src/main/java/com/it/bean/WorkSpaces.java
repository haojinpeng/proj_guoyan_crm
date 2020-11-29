package com.it.bean;

public class WorkSpaces {
    private Long value;
    private String name;
    private Integer id;

    public WorkSpaces() {
    }

    public WorkSpaces(Long value, String name, Integer id) {
        this.value = value;
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "WorkSpaces{" +
                "value=" + value +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
