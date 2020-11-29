package com.it.bean;

public class Station {
    private Integer id;
    private String station_name;

    public Station() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    @Override
    public String toString() {
        return "Station{" +
                ", station_name='" + station_name + '\'' +
                '}';
    }
}
