package DepInfo.dto;

import RankInfo.dto.RankInfoDTO;

public class DepInfoDTO {

	private int depNum; // 부서번호
	private String depname; // 부서명
	
	private DepInfoDTO depInfoDTO;
	private RankInfoDTO rankInfoDTO;

	// 생성자
	public DepInfoDTO() {

	}

	// 게터, 세터
	public int getDepNum() {
		return depNum;
	}

	public void setDepNum(int depNum) {
		this.depNum = depNum;
	}

	public String getDepname() {
		return depname;
	}

	public void setDepname(String depname) {
		this.depname = depname;
	}

	public DepInfoDTO getDepInfoDTO() {
		return depInfoDTO;
	}

	public void setDepInfoDTO(DepInfoDTO depInfoDTO) {
		this.depInfoDTO = depInfoDTO;
	}

	public RankInfoDTO getRankInfoDTO() {
		return rankInfoDTO;
	}

	public void setRankInfoDTO(RankInfoDTO rankInfoDTO) {
		this.rankInfoDTO = rankInfoDTO;
	}
	
	
	

}
