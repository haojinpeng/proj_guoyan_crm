package com.it.bean;
/*？
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键唯一',
  `employee_number` varchar(25) NOT NULL COMMENT '员工号',
  `username` varchar(25) NOT NULL COMMENT '姓名(创建人)',
  `login_name` varchar(25) NOT NULL COMMENT '登录名唯一',
  `password` varchar(50) NOT NULL COMMENT '登录密码',
  `payroll` int(11) NOT NULL COMMENT '0不在职1在职',
  `age` int(11) NOT NULL COMMENT '年龄',
  `sex` int(11) NOT NULL COMMENT '0代表男性1代表女性',
  `nation` int(11) DEFAULT NULL COMMENT '民族对应民族表id',
  `education_level` int(11) DEFAULT NULL COMMENT '教育程度对应教育表id',
  `tel` varchar(15) NOT NULL COMMENT '联系方式',
  `country` int(11) DEFAULT NULL COMMENT '所在国家主键对应国家id',
  `province` int(11) DEFAULT NULL COMMENT '所在省州对应省州表ID',
  `city` int(11) DEFAULT NULL COMMENT '所在城市对应城市表主键',
  `address` varchar(50) NOT NULL COMMENT '详细地址',
  `extends` text COMMENT '扩展字段数据格式json{''hobby'':''打篮球''}',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
 */
public class User {
    private Integer id;
    private String employee_number;
    private String username;
    private String login_name;
    private String password;
    private String payroll;
    private int age;
    private int sex;
    private int nation;
    private int education_level;
    private String tel;
    private int country;
    private int province;
    private int city;
    private String address;
    private String name;   //郝进鹏新增
    private String n_name;
    private String e_name;
    private String c_name;
    private String p_name;
    private String city_name;


    public User() {
    }

    public User(Integer id, String employee_number, String username, String login_name, String password, String payroll, int age, int sex, int nation, int education_level, String tel, int country, int province, int city, String address, String name, String n_name, String e_name, String c_name, String p_name, String city_name) {
        this.id = id;
        this.employee_number = employee_number;
        this.username = username;
        this.login_name = login_name;
        this.password = password;
        this.payroll = payroll;
        this.age = age;
        this.sex = sex;
        this.nation = nation;
        this.education_level = education_level;
        this.tel = tel;
        this.country = country;
        this.province = province;
        this.city = city;
        this.address = address;
        this.name = name;
        this.n_name = n_name;
        this.e_name = e_name;
        this.c_name = c_name;
        this.p_name = p_name;
        this.city_name = city_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployee_number() {
        return employee_number;
    }

    public void setEmployee_number(String employee_number) {
        this.employee_number = employee_number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPayroll() {
        return payroll;
    }

    public void setPayroll(String payroll) {
        this.payroll = payroll;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getNation() {
        return nation;
    }

    public void setNation(int nation) {
        this.nation = nation;
    }

    public int getEducation_level() {
        return education_level;
    }

    public void setEducation_level(int education_level) {
        this.education_level = education_level;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getN_name() {
        return n_name;
    }

    public void setN_name(String n_name) {
        this.n_name = n_name;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
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
        return "User{" +
                "id=" + id +
                ", employee_number='" + employee_number + '\'' +
                ", username='" + username + '\'' +
                ", login_name='" + login_name + '\'' +
                ", password='" + password + '\'' +
                ", payroll='" + payroll + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", nation=" + nation +
                ", education_level=" + education_level +
                ", tel='" + tel + '\'' +
                ", country=" + country +
                ", province=" + province +
                ", city=" + city +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", n_name='" + n_name + '\'' +
                ", e_name='" + e_name + '\'' +
                ", c_name='" + c_name + '\'' +
                ", p_name='" + p_name + '\'' +
                ", city_name='" + city_name + '\'' +
                '}';
    }
}