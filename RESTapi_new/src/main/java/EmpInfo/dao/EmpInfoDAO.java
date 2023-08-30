package EmpInfo.dao;

import java.util.List;

import EmpInfo.dto.EmpInfoDTO;

public interface EmpInfoDAO {
	
	public List<EmpInfoDTO> list(); // 유저 리스트
	public EmpInfoDTO content(int num); // 특정 사원 조회, 사원 번호로 조회
	public void save(EmpInfoDTO dto); // 사원 등록	
	public void update(EmpInfoDTO dto);// 업데이트
	public void delete(int num);// 삭제

}
