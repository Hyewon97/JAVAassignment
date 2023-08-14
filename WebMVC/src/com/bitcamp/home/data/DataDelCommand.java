package com.bitcamp.home.data;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitcamp.home.CommandService;

public class DataDelCommand implements CommandService {

	@Override
	public String pocessStart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		DataVO vo = new DataVO();
		vo.setNo(Integer.parseInt(req.getParameter("no")));
		vo.setUserid((String)req.getSession().getAttribute("userid"));
		
		DataDAO dao = new DataDAO();
		//데이터 베이스 파일명 정보를 먼저 선택해서 보관한다.
		dao.filenameSelect(vo);
		
		//레코드 지우기
		int cnt = dao.dataDelete(vo);
		
		//레코드가 삭제된 경우 파일 삭제
		if(cnt>=1) {
			//경로
			String path = req.getServletContext().getRealPath("/upload");
			for(String dbFile : vo.getFilename()) {
				if(dbFile !=null && !dbFile.equals("")) {
					try {
						File f = new File(path, dbFile);
						f.delete();
					}catch (Exception e) {
						System.out.println("파일삭제 에러 => "+ e.getMessage());
					}
				}
			}
		}
		
		req.setAttribute("cnt", cnt);
		return "/data/delOk.jsp";
	}

}
