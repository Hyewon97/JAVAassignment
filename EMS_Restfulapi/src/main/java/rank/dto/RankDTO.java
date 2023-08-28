package rank.dto;

public class RankDTO {
	
	int rankNum; // 직급 번호
	String rankName; // 직급명
	
	public RankDTO() {
		
	}

	// 게터, 세터 선언
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
