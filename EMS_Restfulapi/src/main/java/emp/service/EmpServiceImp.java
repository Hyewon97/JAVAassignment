package emp.service;

import java.util.List;

import emp.dao.EmpDAO;
import emp.dto.EmpDTO;
import emp.dto.PageDTO;

public class EmpServiceImp implements EmpService{
	private EmpDAO empDao;
	
	public EmpServiceImp() {
		
	}
	
	public void setEmpDao(EmpDAO empDao) {
		this.empDao = empDao;
	}

	@Override
	public int countProcess() {
		return empDao.count();
	}

	@Override
	public List<EmpDTO> listProcess(PageDTO pv) {
		return empDao.list(pv);
	}

}
