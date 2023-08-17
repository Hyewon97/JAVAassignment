package empRegi.model;

import java.sql.Date;

// dto 개념인듯
// 직원 테이블 변수
public class Employee {
	private int empNum;
	private String name;
	private String email;
	private String password;
	private Date hireDate;
	private int depNum;
	
	// 게터, 세터 설정
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public int getDepNum() {
		return depNum;
	}
	public void setDepNum(int depNum) {
		this.depNum = depNum;
	}
	
	
	
}
