package EmpInfo.service;

import java.util.List;

import javax.inject.Inject;

import EmpInfo.dao.EmpInfoDAO;
import EmpInfo.dto.EmpInfoDTO;

public class EmpInfoServiceImp implements EmpInfoService{
	
	@Inject
	private EmpInfoDAO dao;
	

	@Override
	public List<EmpInfoDTO> listProcess() throws Exception{
		return dao.list();
	}
	
	@Override
	public void insertProcess(EmpInfoDTO dto) throws Exception {
		dao.save(dto);
		
	}


	@Override
	public EmpInfoDTO contentProcess(int num)throws Exception {
		return dao.content(num);
				
	}

	@Override
	public void updateProcess(EmpInfoDTO dto) throws Exception {
		dao.update(dto);
		
	}

	@Override
	public void deleteProcess(int num) throws Exception {
		dao.delete(num);
		
	}

	
}
