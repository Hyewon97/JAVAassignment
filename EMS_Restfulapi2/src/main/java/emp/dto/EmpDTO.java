package emp.dto;

import dep.dto.DepDTO;
import rank.dto.RankDTO;

public class EmpDTO {
	
	private int empNum; // 사원 번호
	private String empName, empEmail, depName; // 사원 이름, 사원 이메일, 부서
	private char empSex; // 성별

	private DepDTO depDTO; // 부서 DTO
	private RankDTO rankDTO; // 직급 DTO
	
	// 생성자
	public EmpDTO() {
		
	}

	// 게터, 세터
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

	public char getEmpSex() {
		return empSex;
	}

	public void setEmpSex(char empSex) {
		this.empSex = empSex;
	}

	public DepDTO getDepDTO() {
		return depDTO;
	}

	public void setDepDTO(DepDTO depDTO) {
		this.depDTO = depDTO;
	}

	public RankDTO getRankDTO() {
		return rankDTO;
	}

	public void setRankDTO(RankDTO rankDTO) {
		this.rankDTO = rankDTO;
	}


	
	

}
