package com.it.bean;

public class Business_cost {
    private Integer id;
    private Integer business_id;
    private String business_name;
    private Integer business_tracking;
    private Integer consume_type;
    private String type_name;
    private Double reality_consume;
    private Double perdict_consume;
    private Integer audit_status;
    private Integer cost_declarant;
    private String username;
    private String cost_description;

    public Business_cost() {
    }

    public Business_cost(Integer id, Integer business_id, String business_name, Integer business_tracking, Integer consume_type, String type_name, Double reality_consume, Double perdict_consume, Integer audit_status, Integer cost_declarant, String username, String cost_description) {
        this.id = id;
        this.business_id = business_id;
        this.business_name = business_name;
        this.business_tracking = business_tracking;
        this.consume_type = consume_type;
        this.type_name = type_name;
        this.reality_consume = reality_consume;
        this.perdict_consume = perdict_consume;
        this.audit_status = audit_status;
        this.cost_declarant = cost_declarant;
        this.username = username;
        this.cost_description = cost_description;
    }

    @Override
    public String toString() {
        return "Business_cost{" +
                "id=" + id +
                ", business_id=" + business_id +
                ", business_name='" + business_name + '\'' +
                ", business_tracking=" + business_tracking +
                ", consume_type=" + consume_type +
                ", type_name='" + type_name + '\'' +
                ", reality_consume=" + reality_consume +
                ", perdict_consume=" + perdict_consume +
                ", audit_status=" + audit_status +
                ", cost_declarant=" + cost_declarant +
                ", username='" + username + '\'' +
                ", cost_description='" + cost_description + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getBusiness_tracking() {
        return business_tracking;
    }

    public void setBusiness_tracking(Integer business_tracking) {
        this.business_tracking = business_tracking;
    }

    public Integer getConsume_type() {
        return consume_type;
    }

    public void setConsume_type(Integer consume_type) {
        this.consume_type = consume_type;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public Double getReality_consume() {
        return reality_consume;
    }

    public void setReality_consume(Double reality_consume) {
        this.reality_consume = reality_consume;
    }

    public Double getPerdict_consume() {
        return perdict_consume;
    }

    public void setPerdict_consume(Double perdict_consume) {
        this.perdict_consume = perdict_consume;
    }

    public Integer getAudit_status() {
        return audit_status;
    }

    public void setAudit_status(Integer audit_status) {
        this.audit_status = audit_status;
    }

    public Integer getCost_declarant() {
        return cost_declarant;
    }

    public void setCost_declarant(Integer cost_declarant) {
        this.cost_declarant = cost_declarant;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCost_description() {
        return cost_description;
    }

    public void setCost_description(String cost_description) {
        this.cost_description = cost_description;
    }
}
