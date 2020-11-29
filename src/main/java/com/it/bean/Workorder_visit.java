package com.it.bean;
//工单回访
/*
 * `id` int(11) NOT NULL COMMENT '主键',
  `order_id` int(11) NOT NULL COMMENT '工单对应id',
  `evaluate` int(11) DEFAULT NULL COMMENT '客户满意度1：很满意2：一般3：不满意',
  `suggest` varchar(500) DEFAULT NULL COMMENT '客户满意度',
  `visit_time` varchar(50) NOT NULL COMMENT '回访时间',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人主键',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
 */

public class Workorder_visit {
	private int id;
	private int order_id;
	private int evaluate;
	private String suggest;
	private String visit_time;
	private int create_user;
	private String remark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(int evaluate) {
		this.evaluate = evaluate;
	}
	public String getSuggest() {
		return suggest;
	}
	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
	public String suggest() {
		return suggest;
	}
	public void visit_time(String visit_time) {
		this.visit_time = visit_time;
	}
	public int getCreate_user() {
		return create_user;
	}
	public void setCreate_user(int create_user) {
		this.create_user = create_user;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Workorder_visit(int id, int order_id, int evaluate, String suggest, String visit_time, int create_user,
			String remark) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.evaluate = evaluate;
		this.suggest = suggest;
		this.visit_time = visit_time;
		this.create_user = create_user;
		this.remark = remark;
	}
	public Workorder_visit() {
		super();
	}
	@Override
	public String toString() {
		return "Workorder_visit [id=" + id + ", order_id=" + order_id + ", evaluate=" + evaluate + ", suggest="
				+ suggest + ", visit_time=" + visit_time + ", create_user=" + create_user + ", remark="
				+ remark + "]";
	}
	
	

}
