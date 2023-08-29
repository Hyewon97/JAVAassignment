package emp.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import emp.dto.EmpDTO;
import emp.dto.PageDTO;

public class EmpDaoImp implements EmpDAO{
	private SqlSessionTemplate sqlSession; // mybatis 

	@Override
	public int count() {
		return sqlSession.selectOne("emp.count");
	}

	@Override
	public List<EmpDTO> list(PageDTO pv) {
		return sqlSession.selectList("emp.list",pv);
	}

}
