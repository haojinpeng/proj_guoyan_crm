package com.it.bean;

public class Project_expenses_apply {
    private int id;
    private int project_id;
    private int apply_user;
    private int process_id;
    private String createtime;
    private String apply_money;
    private int apply_type;
    private int apply_status;
    private String apply_description;
    private String gistaddr;
    private String commodity;
    private String remarks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getApply_user() {
        return apply_user;
    }

    public void setApply_user(int apply_user) {
        this.apply_user = apply_user;
    }

    public int getProcess_id() {
        return process_id;
    }

    public void setProcess_id(int process_id) {
        this.process_id = process_id;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getApply_money() {
        return apply_money;
    }

    public void setApply_money(String apply_money) {
        this.apply_money = apply_money;
    }

    public int getApply_type() {
        return apply_type;
    }

    public void setApply_type(int apply_type) {
        this.apply_type = apply_type;
    }

    public int getApply_status() {
        return apply_status;
    }

    public void setApply_status(int apply_status) {
        this.apply_status = apply_status;
    }

    public String getApply_description() {
        return apply_description;
    }

    public void setApply_description(String apply_description) {
        this.apply_description = apply_description;
    }

    public String getGistaddr() {
        return gistaddr;
    }

    public void setGistaddr(String gistaddr) {
        this.gistaddr = gistaddr;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Project_expenses_apply() {
    }

    public Project_expenses_apply(int id, int project_id, int apply_user, int process_id, String createtime, String apply_money, int apply_type, int apply_status, String apply_description, String gistaddr, String commodity, String remarks) {
        this.id = id;
        this.project_id = project_id;
        this.apply_user = apply_user;
        this.process_id = process_id;
        this.createtime = createtime;
        this.apply_money = apply_money;
        this.apply_type = apply_type;
        this.apply_status = apply_status;
        this.apply_description = apply_description;
        this.gistaddr = gistaddr;
        this.commodity = commodity;
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Project_expenses_apply{" +
                "id=" + id +
                ", project_id=" + project_id +
                ", apply_user=" + apply_user +
                ", process_id=" + process_id +
                ", createtime='" + createtime + '\'' +
                ", apply_money='" + apply_money + '\'' +
                ", apply_type=" + apply_type +
                ", apply_status=" + apply_status +
                ", apply_description='" + apply_description + '\'' +
                ", gistaddr='" + gistaddr + '\'' +
                ", commodity='" + commodity + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
