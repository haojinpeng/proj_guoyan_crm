package com.it.bean;

public class City {
    private int id;
    private int province;
    private String city_name;

    public City() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", province=" + province +
                ", city_name='" + city_name + '\'' +
                '}';
    }
}
