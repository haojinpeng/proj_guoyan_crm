package com.it.bean;

public class Jurisdiction {
    private Long id;
    private String name;
    private String path;

    @Override
    public String toString() {
        return "Jurisdiction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    public Jurisdiction() {
    }

    public Jurisdiction(Long id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
