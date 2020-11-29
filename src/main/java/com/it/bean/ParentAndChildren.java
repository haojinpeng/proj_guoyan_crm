package com.it.bean;



import java.util.List;


public class ParentAndChildren {
    protected int id;
    protected  int pid;
    protected List<ParentAndChildren> child;

    public ParentAndChildren() {
    }

    public ParentAndChildren(int id, int pid, List<ParentAndChildren> child) {
        this.id = id;
        this.pid = pid;
        this.child = child;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public List<ParentAndChildren> getChild() {
        return child;
    }

    public void setChild(List<ParentAndChildren> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "ParentAndChildren{" +
                "id=" + id +
                ", pid=" + pid +
                ", child=" + child +
                '}';
    }
}
