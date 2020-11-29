package com.it.bean;

public class Customer {
    private Long id;
    private String customer_name;
    private int country;
    private int province;
    private int city;
    private String address;
    private int clientType;
    private String contact;
    private String c_name;
    private String p_name;
    private String city_name;

    public Customer() {
    }

    public Customer(Long id, String customer_name, int country, int province, int city, String address, int clientType, String contact, String c_name, String p_name, String city_name) {
        this.id = id;
        this.customer_name = customer_name;
        this.country = country;
        this.province = province;
        this.city = city;
        this.address = address;
        this.clientType = clientType;
        this.contact = contact;
        this.c_name = c_name;
        this.p_name = p_name;
        this.city_name = city_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customer_name='" + customer_name + '\'' +
                ", address='" + address + '\'' +
                ", clientType=" + clientType +
                ", contact='" + contact + '\'' +
                ", c_name='" + c_name + '\'' +
                ", p_name='" + p_name + '\'' +
                ", city_name='" + city_name + '\'' +
                '}';
    }
}
