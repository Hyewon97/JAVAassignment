package EmpInfo.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import EmpInfo.dto.EmpInfoDTO;

@Repository
public class EmpInfoImp implements EmpInfoDAO
{
	// sql 템플렛 정의
	@Inject
	private SqlSession session;
	private static String namespace = "com.mycompany.myapp.mapper.empInfoMapper";
	

	@Override
	public List<EmpInfoDTO> list() throws Exception{
		return session.selectList(namespace+".list");
	}

	@Override
	public EmpInfoDTO content(int num) throws Exception{
		return session.selectOne(namespace+".content", num);
	}

	@Override
	public void save(EmpInfoDTO dto) throws Exception{
		session.insert(namespace+".save",dto);
		
	}

	@Override
	public void update(EmpInfoDTO dto) throws Exception {
		session.update(namespace+".update",dto);
		
	}

	@Override
	public void delete(int num) throws Exception{
		session.delete(namespace+".delete",num);
		
	}

}
