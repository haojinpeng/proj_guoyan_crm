package com.it.bean;

public class Workorder {
    private int id;
    private String customer_name;
    private String project_name;
    private String createtime;
    private int workorder_count;
    private int workorder_process_count;
    private int workorder_visit_count;
    static int count = 0;

    public Workorder() {
        this.id = count++;
    }

    public Workorder(int id, String customer_name, String project_name, String createtime, int workorder_count, int workorder_process_count, int workorder_visit_count) {
        this.id = id;
        this.customer_name = customer_name;
        this.project_name = project_name;
        this.createtime = createtime;
        this.workorder_count = workorder_count;
        this.workorder_process_count = workorder_process_count;
        this.workorder_visit_count = workorder_visit_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getWorkorder_count() {
        return workorder_count;
    }

    public void setWorkorder_count(int workorder_count) {
        this.workorder_count = workorder_count;
    }

    public int getWorkorder_process_count() {
        return workorder_process_count;
    }

    public void setWorkorder_process_count(int workorder_process_count) {
        this.workorder_process_count = workorder_process_count;
    }

    public int getWorkorder_visit_count() {
        return workorder_visit_count;
    }

    public void setWorkorder_visit_count(int workorder_visit_count) {
        this.workorder_visit_count = workorder_visit_count;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Workorder.count = count;
    }

    @Override
    public String toString() {
        return "Workorder{" +
                "id=" + id +
                ", customer_name='" + customer_name + '\'' +
                ", project_name='" + project_name + '\'' +
                ", createtime='" + createtime + '\'' +
                ", workorder_count=" + workorder_count +
                ", workorder_process_count=" + workorder_process_count +
                ", workorder_visit_count=" + workorder_visit_count +
                '}';
    }
}
