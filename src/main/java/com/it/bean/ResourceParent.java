package com.it.bean;

import java.util.List;

public class ResourceParent {
    protected String name;
    protected String path;
    protected String type;
    protected String href;
    protected String title;
    protected String icon;
    protected String target="_self";
    protected List<ResourceParent> child;

    @Override
    public String toString() {
        return "ResourceParent{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", type='" + type + '\'' +
                ", child=" + child +
                ", href='" + href + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public ResourceParent() {
    }

    public ResourceParent(String name, String path, String type, List<ResourceParent> child, String href, String title) {
        this.name = name;
        this.path = path;
        this.type = type;
        this.child = child;
        this.href = href;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.title=name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        this.href=path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        if (type.equals("M")){
            this.icon="fa fa-tachometer";
        }else{
            this.icon="fa fa-home";
        }
    }

    public List<ResourceParent> getChild() {
        return child;
    }

    public void setChild(List<ResourceParent> child) {
        this.child = child;
    }

    public String getHref() {
        return href;
    }


    public String getTitle() {
        return title;
    }

}
