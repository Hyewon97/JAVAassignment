package com.bitcamp.home.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitcamp.home.CommandService;

public class MemberOkCommand implements CommandService {

	@Override
	public String pocessStart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 뷰에서 정보를 서버로 가져오기
		req.setCharacterEncoding("UTF-8");
		MemberVO vo =new MemberVO();
		vo.setUserid(req.getParameter("userid"));
		vo.setUsepwd(req.getParameter("userpwd"));
		vo.setUsername(req.getParameter("username"));
		vo.setTel1(req.getParameter("tel1"));
		vo.setTel2(req.getParameter("tel2"));
		vo.setTel3(req.getParameter("tel3"));
		vo.setEmailid(req.getParameter("emailid"));
		vo.setEmaildomain(req.getParameter("emaildomain"));
		vo.setZipcode(req.getParameter("zipcode"));
		vo.setAddr(req.getParameter("addr"));
		vo.setDetailaddr(req.getParameter("detailaddr"));
		vo.setInterest(req.getParameterValues("interest"));
		
		//DB에 추가
		MemberDAO dao= MemberDAO.getInstance();
		int cnt=dao.setNewMember(vo);//////////////////////////////////////써야함
		
		//뷰로 내보내기
		req.setAttribute("result", cnt);
		
		return "/member/memberResult.jsp";
	}

}
