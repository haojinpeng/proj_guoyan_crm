package com.it.bean;

import java.util.List;

public class Resource extends ResourceParent{
    private Long id;

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", type='" + type + '\'' +
                ", href='" + href + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", target='" + target + '\'' +
                ", child=" + child +
                '}';
    }

    public Resource() {

    }

    public Resource(Long id) {
        this.id = id;
    }

    public Resource(String name, String path, String type, List<ResourceParent> child, String href, String title, Long id) {
        super(name, path, type, child, href, title);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
