package EmpInfo.service;

import java.util.List;

import EmpInfo.dao.EmpInfoDAO;
import EmpInfo.dto.EmpInfoDTO;

public class EmpInfoServiceImp implements EmpInfoService{
	
	private EmpInfoDAO empInfoDao;
	
	public EmpInfoServiceImp() {
		
		
	}
	
	public void setEmpInfoDao(EmpInfoDAO empInfoDao) {
		this.empInfoDao = empInfoDao;
	}

	@Override
	public List<EmpInfoDTO> listProcess() {
		return empInfoDao.list();
	}
	
	@Override
	public void insertProcess(EmpInfoDTO dto) {
		empInfoDao.save(dto);
		
	}


	@Override
	public EmpInfoDTO contentProcess(int num) {
		return empInfoDao.content(num);
				
	}

	@Override
	public void updateProcess(EmpInfoDTO dto) {
		empInfoDao.update(dto);
		
	}

	@Override
	public void deleteProcess(int num) {
		empInfoDao.delete(num);
		
	}

	
}
