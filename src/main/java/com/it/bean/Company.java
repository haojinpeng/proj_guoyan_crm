package com.it.bean;

public class Company {
    private Long company_id;
    private String company_type;

    public Long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Long company_id) {
        this.company_id = company_id;
    }

    public String getCompany_type() {
        return company_type;
    }

    public void setCompany_type(String company_type) {
        this.company_type = company_type;
    }

    @Override
    public String toString() {
        return "Company{" +
                "company_id='" + company_id + '\'' +
                ", company_type='" + company_type + '\'' +
                '}';
    }

    public Company(Long company_id, String company_type) {
        this.company_id = company_id;
        this.company_type = company_type;
    }

    public Company() {
    }
}
