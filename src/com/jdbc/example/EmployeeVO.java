package com.jdbc.example;

public class EmployeeVO {
	//단순히 값을 저장하기 위한 클래스
	
	//1. 테이블이랑 맴버변수를 동일하게 생성한다.
	//2. 기본생성자, 모든 맴버변수를 저장하는 생성자를 만든다.
	//3. getter / setter
	
	private int emp_id;
	private String name;
	private int salary;
	private String dep_name;
	
	public EmployeeVO() {
	}
	
	
	
	public EmployeeVO(int emp_id, String name, int salary, String dep_name) {
		super();
		this.emp_id = emp_id;
		this.name = name;
		this.salary = salary;
		this.dep_name = dep_name;
	}



	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}



	public String getDep_name() {
		return dep_name;
	}



	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}

	

	
	
	
}
