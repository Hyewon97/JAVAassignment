package emp.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import emp.dto.EmpDTO;

@Repository
public class EmpDaoImp implements EmpDAO{
	
	@Inject
	private SqlSession session; // mybatis 
	
	@Inject
	private EmpDAO empDAO;

	@Override
	public void create(EmpDTO empDTO) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EmpDTO read(Integer empNum) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(EmpDTO empDTO) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer empNum) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EmpDTO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
