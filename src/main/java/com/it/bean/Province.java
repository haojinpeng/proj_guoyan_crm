package com.it.bean;

public class Province {
    private int id;
    private int country;
    private String p_name;

    public Province() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", country=" + country +
                ", p_name='" + p_name + '\'' +
                '}';
    }
}
