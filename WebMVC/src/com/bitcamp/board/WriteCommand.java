package com.bitcamp.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitcamp.home.CommandService;

public class WriteCommand implements CommandService {

	@Override
	public String pocessStart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//글쓰기폼
		String viewFilename="";
		
		HttpSession ses = req.getSession();
		String sesUserid = (String)ses.getAttribute("userid");
		
		if(sesUserid!=null && !sesUserid.equals("")) {//로그인 글쓰기폼
			viewFilename = "/board/boardForm.jsp";
		}else {//로그인 안된경우 로그인폼
			viewFilename = "/member/login.jsp";
		}
		return viewFilename;
	}

}
