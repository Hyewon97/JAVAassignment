package emp.service;

import java.util.List;

import emp.dto.EmpDTO;
import emp.dto.PageDTO;

public interface EmpService {
	
	// 일단 간단하게 2개만띄워보고 추가하는 걸로
	public int countProcess(); // 페이징 처리를 위해 데이터의 갯수를 읽음
	public List<EmpDTO> listProcess(PageDTO pv); // 페이지를 위한 listPorcess
	

}
