package com.bitcamp.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitcamp.home.CommandService;

public class WriteOkCommand2 implements CommandService {

	@Override
	public String pocessStart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession ses = req.getSession();
		String sesUserid = (String)ses.getAttribute("userid");
		String viewFilename="";
		
		if(sesUserid !=null && !sesUserid.equals("")) {
			req.setCharacterEncoding("UTF-8");
			//글등록
			BoardVO vo = new BoardVO();
			vo.setSubject(req.getParameter("subject"));//제목
			vo.setContent(req.getParameter(""));
		}else {
			
		}
		return viewFilename;
	}

}
