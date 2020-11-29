package com.it.bean;

public class ResourceAndJurisdiction {
    private Long resource_id;
    private String name;
    private String path;
    private String type;
    private String jurisdiction_id;
    private String jurisdiction_name;

    @Override
    public String toString() {
        return "ResourceAndJurisdiction{" +
                "resource_id=" + resource_id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", type='" + type + '\'' +
                ", jurisdiction_id='" + jurisdiction_id + '\'' +
                ", jurisdiction_name='" + jurisdiction_name + '\'' +
                '}';
    }

    public ResourceAndJurisdiction() {
    }

    public ResourceAndJurisdiction(Long resource_id, String name, String path, String type, String jurisdiction_id, String jurisdiction_name) {
        this.resource_id = resource_id;
        this.name = name;
        this.path = path;
        this.type = type;
        this.jurisdiction_id = jurisdiction_id;
        this.jurisdiction_name = jurisdiction_name;
    }

    public Long getResource_id() {
        return resource_id;
    }

    public void setResource_id(Long resource_id) {
        this.resource_id = resource_id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJurisdiction_id() {
        return jurisdiction_id;
    }

    public void setJurisdiction_id(String jurisdiction_id) {
        this.jurisdiction_id = jurisdiction_id;
    }

    public String getJurisdiction_name() {
        return jurisdiction_name;
    }

    public void setJurisdiction_name(String jurisdiction_name) {
        this.jurisdiction_name = jurisdiction_name;
    }
}
