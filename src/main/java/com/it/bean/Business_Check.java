package com.it.bean;

public class Business_Check {
    private Integer id;
    private Integer cost_id;
    private Integer business_id;
    private String business_name;
    private Integer userp;
    private String username;
    private Integer check_condition;
    private String check_idea;
    private String check_date;
    private String remarks;

    public Business_Check() {
    }

    public Business_Check(Integer id, Integer cost_id, Integer business_id, String business_name, Integer userp, String username, Integer check_condition, String check_idea, String check_date, String remarks) {
        this.id = id;
        this.cost_id = cost_id;
        this.business_id = business_id;
        this.business_name = business_name;
        this.userp = userp;
        this.username = username;
        this.check_condition = check_condition;
        this.check_idea = check_idea;
        this.check_date = check_date;
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Business_Check{" +
                "id=" + id +
                ", cost_id=" + cost_id +
                ", business_id=" + business_id +
                ", business_name='" + business_name + '\'' +
                ", userp=" + userp +
                ", username='" + username + '\'' +
                ", check_condition=" + check_condition +
                ", check_idea='" + check_idea + '\'' +
                ", check_date='" + check_date + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCost_id() {
        return cost_id;
    }

    public void setCost_id(Integer cost_id) {
        this.cost_id = cost_id;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public Integer getUserp() {
        return userp;
    }

    public void setUserp(Integer userp) {
        this.userp = userp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCheck_condition() {
        return check_condition;
    }

    public void setCheck_condition(Integer check_condition) {
        this.check_condition = check_condition;
    }

    public String getCheck_idea() {
        return check_idea;
    }

    public void setCheck_idea(String check_idea) {
        this.check_idea = check_idea;
    }

    public String getCheck_date() {
        return check_date;
    }

    public void setCheck_date(String check_date) {
        this.check_date = check_date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
