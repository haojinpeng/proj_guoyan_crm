package com.it.bean;

public class Project_expenses_approval {
    private int id;
    private int expenses_id;
    private int reviewer_id;
    private String createtime;
    private int status;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExpenses_id() {
        return expenses_id;
    }

    public void setExpenses_id(int expenses_id) {
        this.expenses_id = expenses_id;
    }

    public int getReviewer_id() {
        return reviewer_id;
    }

    public void setReviewer_id(int reviewer_id) {
        this.reviewer_id = reviewer_id;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project_expenses_approval(int id, int expenses_id, int reviewer_id, String createtime, int status, String description) {
        this.id = id;
        this.expenses_id = expenses_id;
        this.reviewer_id = reviewer_id;
        this.createtime = createtime;
        this.status = status;
        this.description = description;
    }

    public Project_expenses_approval() {
    }

    @Override
    public String toString() {
        return "Project_expenses_approval{" +
                "id=" + id +
                ", expenses_id=" + expenses_id +
                ", reviewer_id=" + reviewer_id +
                ", createtime='" + createtime + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}
