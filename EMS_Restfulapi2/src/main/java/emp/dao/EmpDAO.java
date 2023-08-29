package emp.dao;

import java.util.List;

import emp.dto.EmpDTO;
import emp.dto.PageDTO;

public interface EmpDAO {
	
	public void create(EmpDTO empDTO) throws Exception;
	public EmpDTO read(Integer empNum) throws Exception;
	public void update(EmpDTO empDTO) throws Exception; //
	public void delete(int empNum) throws Exception; // 사원 번호로 삭제
	public List<EmpDTO> listAll() throws Exception; // 전체 목록 출력

}


/*
db 연동은 된거 같은데 컨트롤러랑 서비스 부분 수정 안했음.
*/