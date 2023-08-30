package RankInfo.dto;

public class RankInfoDTO {
	
	private int rankNum; // 직급 번호
	private String rankName; // 직급명
	
	// 생성자
	public RankInfoDTO() {
		
	}
	
	// 게터, 세터
	public int getRankNum() {
		return rankNum;
	}
	public void setRankNum(int rankNum) {
		this.rankNum = rankNum;
	}
	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	
	

}
