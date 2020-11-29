package com.it.bean;
/*
*  `

  `
  `email` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '联系人邮箱',
  `qq` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '联系人qq',
  `wechat` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '联系人微信',
  `address` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '联系人地址',
  `extends` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '扩展字段数据格式为{‘hobby’:‘唱歌’}',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
* */
public class Contact {
    private Long id;
    private String name;
    private int sex;
    private int age;
    private int type;
    private int nation;
    private int country;
    private int province;
    private int city;
    private String position;
    private String company;
    private String phone;
    private String email;
    private String qq;
    private String wechat;
    private String address;
    private String n_name;
    private String c_name;
    private String p_name;
    private String city_name;
    private String username;


    public Contact() {
    }

    public Contact(Long id, String name, int sex, int age, int type, int nation, int country, int province, int city, String position, String company, String phone, String email, String qq, String wechat, String address, String n_name, String c_name, String p_name, String city_name,String username) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.type = type;
        this.nation = nation;
        this.country = country;
        this.province = province;
        this.city = city;
        this.position = position;
        this.company = company;
        this.phone = phone;
        this.email = email;
        this.qq = qq;
        this.wechat = wechat;
        this.address = address;
        this.n_name = n_name;
        this.c_name = c_name;
        this.p_name = p_name;
        this.city_name = city_name;
        this.username = username;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNation() {
        return nation;
    }

    public void setNation(int nation) {
        this.nation = nation;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getN_name() {
        return n_name;
    }

    public void setN_name(String n_name) {
        this.n_name = n_name;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", type=" + type +
                ", nation=" + nation +
                ", country=" + country +
                ", province=" + province +
                ", city=" + city +
                ", position='" + position + '\'' +
                ", company='" + company + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                ", wechat='" + wechat + '\'' +
                ", address='" + address + '\'' +
                ", n_name='" + n_name + '\'' +
                ", c_name='" + c_name + '\'' +
                ", p_name='" + p_name + '\'' +
                ", city_name='" + city_name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
