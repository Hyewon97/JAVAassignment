package empRegi.model;



public class Employee {
	private int empNum; // 사원번호
	private String name; // 이름
	private String email; // 이메일
	private String department; // 부서
	
	public Employee() {
		
	}
	
	public Employee(String name, String email, String department) {
		super();
		this.name = name;
		this.email = email;
		this.department = department;
	}

	public Employee(int empNum, String name, String email, String department) {
		super();
		this.empNum = empNum;
		this.name = name;
		this.email = email;
		this.department = department;
	}

	// 게터, 세터 설정
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
