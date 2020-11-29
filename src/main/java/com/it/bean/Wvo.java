package com.it.bean;

public class Wvo {
	//��¼
	private int id;
	private String number;//������
	private int recive_uer;//����������
	private int customer_id;//�ͻ�����
	private int project_id;//��Ŀ����
	private  String receive_time;//����ʱ��
	private String require_time;//��ֹʱ��
	private int status;//����״̬0���Ѵ���1��δ����2����ת��
	private String problem_description;//��������
	//�ط�
	private int order_id;//������Ӧid
	private int evaluate;//�ͻ������1��������2��һ��3��������
	private String suggest;//�ͻ������
	private String visit_time;//�ط�ʱ��
	private int create_user;//����������
	private String remark;//��ע
	//����
	private int workorder_id;//��������
	private int forward_user;//ת����(������)����
	private int handle_user;//����������
	private String problem_solve;//����������
	private String remarks;//����
	//user
	private String username;//������
	
	//project
	private String project_name;//��Ŀ����
	
	//customer
	
	private String customer_name;//�ͻ�����


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getRecive_uer() {
		return recive_uer;
	}

	public void setRecive_uer(int recive_uer) {
		this.recive_uer = recive_uer;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getReceive_time() {
		return receive_time;
	}

	public void setReceive_time(String receive_time) {
		this.receive_time = receive_time;
	}

	public String getRequire_time() {
		return require_time;
	}

	public void setRequire_time(String require_time) {
		this.require_time = require_time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getProblem_description() {
		return problem_description;
	}

	public void setProblem_description(String problem_description) {
		this.problem_description = problem_description;
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

	public String getVisit_time() {
		return visit_time;
	}

	public void setVisit_time(String visit_time) {
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public Wvo() {
	}

	@Override
	public String toString() {
		return "Wvo{" +
				"id=" + id +
				", number='" + number + '\'' +
				", recive_uer=" + recive_uer +
				", customer_id=" + customer_id +
				", project_id=" + project_id +
				", receive_time='" + receive_time + '\'' +
				", require_time='" + require_time + '\'' +
				", status=" + status +
				", problem_description='" + problem_description + '\'' +
				", order_id=" + order_id +
				", evaluate=" + evaluate +
				", suggest='" + suggest + '\'' +
				", visit_time='" + visit_time + '\'' +
				", create_user=" + create_user +
				", remark='" + remark + '\'' +
				", workorder_id=" + workorder_id +
				", forward_user='" + forward_user + '\'' +
				", handle_user=" + handle_user +
				", problem_solve=" + problem_solve +
				", remarks='" + remarks + '\'' +
				", username='" + username + '\'' +
				", project_name='" + project_name + '\'' +
				", customer_name='" + customer_name + '\'' +
				'}';
	}
}
