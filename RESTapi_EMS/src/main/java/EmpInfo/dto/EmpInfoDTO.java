package EmpInfo.dto;

// DTO 선언
public class EmpInfoDTO {
	
	private int empNum; // 사원 번호
	private String empName; // 사원 이름
	private char empSex; // 사원 성별 0이면 남자, 1이면 여자
	private String empEmail; // 사원 이메일
	private String depName; // 부서명
	private String rankName;
	
	// 생성자
	public EmpInfoDTO() {
		
	}

	// 게터, 세터 선언
	public int getEmpNum() {
		return empNum;
	}

	public void setEmpNum(int empNum) {
		this.empNum = empNum;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public char getEmpSex() {
		return empSex;
	}

	public void setEmpSex(char empSex) {
		this.empSex = empSex;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getRankName() {
		return rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	
	
	

}
