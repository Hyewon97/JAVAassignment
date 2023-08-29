package emp.dao;

import java.util.List;

import emp.dto.EmpDTO;
import emp.dto.PageDTO;

public interface EmpDAO {
	
	public void create(EmpDTO empDTO) throws Exception;
	public EmpDTO read(Integer empNum) throws Exception;
	public void update(EmpDTO empDTO) throws Exception;
	public void delete(Integer empNum) throws Exception;
	public List<EmpDTO> listAll() throws Exception;

}
