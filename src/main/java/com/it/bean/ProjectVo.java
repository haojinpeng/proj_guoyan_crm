package com.it.bean;

public class ProjectVo {
    private int project_id;
    private String project_name;
    private String createtime;
    private String name;
    private int manager_id;
    private int plan_time;
    private int actul_time;
    private int status;

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    @Override
    public String toString() {
        return "ProjectVo{" +
                "project_id=" + project_id +
                ", project_name='" + project_name + '\'' +
                ", createtime='" + createtime + '\'' +
                ", name='" + name + '\'' +
                ", manager_id=" + manager_id +
                ", plan_time=" + plan_time +
                ", actul_time=" + actul_time +
                ", status=" + status +
                '}';
    }


    public ProjectVo(int project_id, String project_name, String createtime, String name, int manager_id, int plan_time, int actul_time, int status) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.createtime = createtime;
        this.name = name;
        this.manager_id = manager_id;
        this.plan_time = plan_time;
        this.actul_time = actul_time;
        this.status = status;
    }

    public ProjectVo() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public int getPlan_time() {
        return plan_time;
    }

    public void setPlan_time(int plan_time) {
        this.plan_time = plan_time;
    }

    public int getActul_time() {
        return actul_time;
    }

    public void setActul_time(int actul_time) {
        this.actul_time = actul_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
