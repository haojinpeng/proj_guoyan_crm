package com.it.bean;
//工单处理
/*
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `workorder_id` int(11) NOT NULL COMMENT '工单主键',
  `forward_user` int(11) DEFAULT NULL COMMENT '转发人主键',
  `handle_user` int(11) DEFAULT NULL COMMENT '处理人主键',
  `problem_solve` varchar(255) DEFAULT NULL COMMENT '问题解决方案',
  `status` int(11) NOT NULL COMMENT '状态0：未解决1：已解决2：转发',
  `remarks` varchar(255) DEFAULT NULL COMMENT '描述',
 */

public class Workorder_process {
	private int id;
	private int workorder_id;
	private int forward_user;
	private int handle_user;
	private String problem_solve;
	private int status;
	private String remarks;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWorkorder_id() {
		return workorder_id;
	}
	public void setWorkorder_id(int workorder_id) {
		this.workorder_id = workorder_id;
	}
	public int getForward_user() {
		return forward_user;
	}
	public void setForward_user(int forward_user) {
		this.forward_user = forward_user;
	}
	public int getHandle_user() {
		return handle_user;
	}
	public void setHandle_user(int handle_user) {
		this.handle_user = handle_user;
	}
	public String getProblem_solve() {
		return problem_solve;
	}
	public void setProblem_solve(String problem_solve) {
		this.problem_solve = problem_solve;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Workorder_process(int id, int workorder_id, int forward_user, int handle_user, String problem_solve,
			int status, String remarks) {
		super();
		this.id = id;
		this.workorder_id = workorder_id;
		this.forward_user = forward_user;
		this.handle_user = handle_user;
		this.problem_solve = problem_solve;
		this.status = status;
		this.remarks = remarks;
	}
	public Workorder_process() {
		super();
	}
	@Override
	public String toString() {
		return "Workorder_process [id=" + id + ", workorder_id=" + workorder_id + ", forward_user=" + forward_user
				+ ", handle_user=" + handle_user + ", problem_solve=" + problem_solve + ", status=" + status
				+ ", remarks=" + remarks + "]";
	}

	
}
