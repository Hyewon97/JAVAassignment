package com.bitcamp.home.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitcamp.home.CommandService;

public class LoginOkCommand implements CommandService {

	@Override
	public String pocessStart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			MemberVO vo = new MemberVO();
			
			vo.setUserid(req.getParameter("userid"));
			vo.setUsepwd(req.getParameter("userpwd"));
			
			MemberDAO dao = MemberDAO.getInstance();
			
			dao.loginCheck(vo);
			
			req.setAttribute("vo", vo);
			
			//로그인 성공시 세션에 필요한데이터 넣기
			if(vo.getUsername()!=null && !vo.getUsername().equals("")) {
				HttpSession ses = req.getSession();
				ses.setAttribute("userid", vo.getUserid());
				ses.setAttribute("username", vo.getUsername());
			}
		
			System.out.println("vo.getUserid() = " + vo.getUserid() + "  vo.getUsername() = " + vo.getUsername() + "  vo.getUserpassword = " + vo.getUsepwd());
		return "/member/loginOk.jsp";
	}

}
