package com.it.bean;

public class Procurement {
    private String Procurement_id;
    private String Procurement_type;
    private String choose;

    public String getProcurement_id() {
        return Procurement_id;
    }

    public void setProcurement_id(String procurement_id) {
        Procurement_id = procurement_id;
    }

    public String getProcurement_type() {
        return Procurement_type;
    }

    public void setProcurement_type(String procurement_type) {
        Procurement_type = procurement_type;
    }

    public String getChoose() {
        return choose;
    }

    public void setChoose(String choose) {
        this.choose = choose;
    }

    @Override
    public String toString() {
        return "Procurement{" +
                "Procurement_id='" + Procurement_id + '\'' +
                ", Procurement_type='" + Procurement_type + '\'' +
                ", choose='" + choose + '\'' +
                '}';
    }

    public Procurement(String procurement_id, String procurement_type, String choose) {
        Procurement_id = procurement_id;
        Procurement_type = procurement_type;
        this.choose = choose;
    }

    public Procurement() {
    }
}
