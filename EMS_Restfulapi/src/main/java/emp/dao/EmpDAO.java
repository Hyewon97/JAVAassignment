package emp.dao;

import java.util.List;

import emp.dto.EmpDTO;
import emp.dto.PageDTO;

public interface EmpDAO {
	
	public int count(); // 페이징 처리
	public List<EmpDTO> list(PageDTO pv);

}
