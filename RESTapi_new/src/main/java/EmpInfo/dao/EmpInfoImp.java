package EmpInfo.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import EmpInfo.dto.EmpInfoDTO;

public class EmpInfoImp implements EmpInfoDAO
{
	// sql 템플렛 정의
	private SqlSessionTemplate sqlSession;
	
	// 생성자
	public EmpInfoImp() {
		
	}
	
	// setSql
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<EmpInfoDTO> list() {
		return sqlSession.selectList("EmpInfo.list");
	}

	@Override
	public EmpInfoDTO content(int num) {
		return sqlSession.selectOne("EmpInfo.content", num);
	}

	@Override
	public void save(EmpInfoDTO dto) {
		sqlSession.insert("EmpInfo.save",dto);
		
	}

	@Override
	public void update(EmpInfoDTO dto) {
		sqlSession.update("EmpInfo.update",dto);
		
	}

	@Override
	public void delete(int num) {
		sqlSession.delete("EmpInfo.delete",num);
		
	}

}
