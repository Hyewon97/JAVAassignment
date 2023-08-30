package EmpInfo.service;

import java.util.List;

import EmpInfo.dto.EmpInfoDTO;

public interface EmpInfoService {
	public List<EmpInfoDTO> listProcess() throws Exception;
	public void insertProcess(EmpInfoDTO dto) throws Exception;
	public EmpInfoDTO contentProcess(int num) throws Exception; 
	public void updateProcess(EmpInfoDTO dto) throws Exception; // 살짝 꼬일 수 있겠는데
	public void deleteProcess(int num) throws Exception;

}
