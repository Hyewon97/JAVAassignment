package com.bitcamp.home.data;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitcamp.home.CommandService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class DataFormOkCommand implements CommandService {

	@Override
	public String pocessStart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//파일업로드할 위치의 절대경로 구하기
		String path = req.getServletContext().getRealPath("/upload");
		System.out.println("path --> " + path);
		
		//1. request객체
		//2. 서버에 파일 업로드가 될 위치(절대경로)
		//3. 업로드 가능최대크기)
		//4. Encoding
		//5. 파일명 rename
		
		int maxSize = 1024*1024*1024;
		DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy(); //rename을 만들어주기위한 객체생성
		
		//form의 데이터와 파일 업로드 완료
		//					req객체, upload위치, 업로드최대크기, 인코딩, rename 
		MultipartRequest mr = new MultipartRequest(req, path, maxSize, "UTF-8", policy);
		
		DataVO vo = new DataVO();
		vo.setTitle(mr.getParameter("title"));
		vo.setContent(mr.getParameter("content"));
		
		HttpSession ses = req.getSession();
		vo.setUserid((String)ses.getAttribute("userid"));
		
		vo.setIp(req.getRemoteAddr());
		
		//폼의 type='file'인 태그의 name속성의 값을 구해서 뿌려준다 fileList.nextElement() ==> 즉 변경된 파일명을 보여줌
		Enumeration fileList = mr.getFileNames();
		int idx=0;
		while(fileList.hasMoreElements()) {
			//System.out.println("fileList => " + fileList.nextElement());
			String nameAttr = (String)fileList.nextElement();//필드명
			//파일명 얻어오기  getOrinalFIleName메소드는 원래파일명 FilesystemName은 바뀐파일명을 구하는 메소드이다. 같으면서 좀 다른거임
			String newFilename = mr.getFilesystemName(nameAttr);//파일명 얻어오는데,중복이 있을경우 바뀐 파일명임
				//mr.getOriginalFileName(nameAttr); //파일명을 얻어오는데 이건 원본파일명을 불러옴
			//System.out.println("newFIlename = " + newFilename);
			////////////////////////////////////////////////////////////////////////////
			if(newFilename != null) {
				vo.getFilename()[idx++]=newFilename;
			}	
		}	
		DataDAO dao = new DataDAO();
		int cnt = dao.dataInsert(vo);
		
		//레코드 추가 실패시 이미 업로드 된 파이을 삭제한다.
		if(cnt<= 0) {
			for(String delfile: vo.getFilename()) {
				if(delfile !=null) {
					try {
						File f = new File(path, delfile);
						f.delete();
					}catch (Exception e) {
						System.out.println("파일삭제 에러");
					}
				}
			}
		}
		req.setAttribute("cnt", cnt);
		return "/data/dataFormOk.jsp";
	}

}
