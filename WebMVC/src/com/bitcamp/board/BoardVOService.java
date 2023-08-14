package com.bitcamp.board;

import java.util.List;

public interface BoardVOService {
	//레코드 추가 - 글쓰기(insert)
	public int oneRecordInsert(BoardVO vo);
	
	//레코드 선택(Record) -글 내용보기, 글수정폼(select) void, VO둘다가능
//	public BoardVO oneRecordSelect(BoardVO vo);
	public void oneRecordSelect(BoardVO vo);
	
	//레코드 삭제 - 글삭제
//	public int boardDelete(BoardVO vo);
	public int boardDelete(int no, String userid);
	
	//조회수 증가
	public void hitCount(int no);
	
	//총 레코드 수
	public int totalRecord(PageSearchVO vo);
	
	//레코드 수정 - update
	public int boardUpdate(BoardVO vo);
	
	//레코드 선택 (1page)
	public List<BoardVO> onePageRecordSelect(PageSearchVO vo);
	
	//글쓴이 선택
	public String getUesrid(int no);
}
