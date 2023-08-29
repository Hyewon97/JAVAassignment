package emp.service;

import java.util.List;

import emp.dto.EmpDTO;
import emp.dto.PageDTO;

public interface EmpService {
	
	public int countProcess();
	public List<EmpDTO> listProcess(PageDTO pv);

}
