package ems.model;

public class EmsUser {
	private int empNum; // 사원 번호
	private String name; // 사원 이름
	private String email; // 이메일
	private String department; // 부서

	// empNum 있는 constructor
	public EmsUser(int empNum, String name, String email, String department) {
		super();
		this.empNum = empNum;
		this.name = name;
		this.email = email;
		this.department = department;
	}
	
	// empNum 없는 constructor
	public EmsUser(String name, String email, String department) {
		super();
		this.name = name;
		this.email = email;
		this.department = department;
	}

	// 게터세터 선언
	public int getEmpNum() {
		return empNum;
	}
	
	public void setEmpNum(int empNum) {
		this.empNum = empNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	

}
