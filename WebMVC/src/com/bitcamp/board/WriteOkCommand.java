package com.bitcamp.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.bitcamp.home.CommandService;

public class WriteOkCommand implements CommandService {

	@Override
	public String pocessStart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession ses = req.getSession(); //세션값 가져오기
		//1. 데이터를 가져온다.
		BoardVO vo = new BoardVO();
		//no, subject, content, userid, hit(앤없어도댐), writedate(sysdate), ip
		
		//제목 가져오기
		vo.setSubject(req.getParameter("subject"));
		
		//컨텐츠(내용) 가져오기
		vo.setContent(req.getParameter("content"));
		
		//로그인한 아이디 가져온다
		vo.setUserid((String)ses.getAttribute("userid"));
		
		//아이피를 가져온다
		vo.setIp(req.getRemoteAddr());	
		
		//2. db에 넣어준다.
		BoardDAO dao = new BoardDAO();
		dao.oneRecordInsert(vo);
		
		System.out.println(" 글등록 완료");
		return "/board/list.do";
	}

}
