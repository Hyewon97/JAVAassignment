package board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import board.dto.BoardDTO;
import board.dto.PageDTO;

public class BoardDaoImp implements BoardDAO{
	private SqlSessionTemplate sqlSession;
	
	public BoardDaoImp() {
	
	}
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int count() {
		return sqlSession.selectOne("board.count");
	}

	@Override
	public List<BoardDTO> list(PageDTO pv) {
		return sqlSession.selectList("board.list", pv);
	}

	@Override
	public void readCount(int num) {
		// 조회수 증가
		sqlSession.update("board.readCount",num);
		
	}

	@Override
	public BoardDTO content(int num) {
		return sqlSession.selectOne("board.content", num);
		// 이걸 하기 전에 조회수를 처리하는 쿼리문이 먼저 실행이 되어야 한다.
	}

	@Override
	public void reStepCount(BoardDTO dto) {
		sqlSession.update("board.reStepCount",dto);
		
	}

	@Override
	public void save(BoardDTO dto) {
		sqlSession.insert("board.save",dto);
		
	}

	@Override
	public void update(BoardDTO dto) {
		sqlSession.update("board.update",dto);
		
	}

	@Override
	public void delete(int num) {
		sqlSession.delete("board.delete",num);
		
	}

	@Override
	public String getFile(int num) {
		return sqlSession.selectOne("board.getFile",num); // 스트링을 리턴해서 메소드 리턴 타입도 스트링
	}
	
	
}
