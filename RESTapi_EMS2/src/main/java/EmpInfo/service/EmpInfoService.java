package EmpInfo.service;

import java.util.List;

import EmpInfo.dto.EmpInfoDTO;

public interface EmpInfoService {
	public List<EmpInfoDTO> listProcess();
	public void insertProcess(EmpInfoDTO dto);
	public EmpInfoDTO contentProcess(int num); 
	public void updateProcess(EmpInfoDTO dto); // 살짝 꼬일 수 있겠는데
	public void deleteProcess(int num);

}
