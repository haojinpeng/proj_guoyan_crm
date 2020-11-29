package com.it.bean;

public class Resources_jurisdiction {
    private Long resourceid;
    private Long jurisdiction;

    @Override
    public String toString() {
        return "Resources_jurisdiction{" +
                "resourceid=" + resourceid +
                ", jurisdiction=" + jurisdiction +
                '}';
    }

    public Resources_jurisdiction() {
    }

    public Resources_jurisdiction(Long resourceid, Long jurisdiction) {
        this.resourceid = resourceid;
        this.jurisdiction = jurisdiction;
    }

    public Long getResourceid() {
        return resourceid;
    }

    public void setResourceid(Long resourceid) {
        this.resourceid = resourceid;
    }

    public Long getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(Long jurisdiction) {
        this.jurisdiction = jurisdiction;
    }
}
