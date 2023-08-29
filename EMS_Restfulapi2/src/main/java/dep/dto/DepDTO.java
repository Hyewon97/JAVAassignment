package dep.dto;

public class DepDTO {
	
	int depNum; // 부서 번호
	String depName; // 부서명
	
	public DepDTO() {
	
	}

	// 게터, 세터 선언
	public int getDepNum() {
		return depNum;
	}

	public void setDepNum(int depNum) {
		this.depNum = depNum;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}
	
	
	

}
