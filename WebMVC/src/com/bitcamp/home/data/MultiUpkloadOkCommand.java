package com.bitcamp.home.data;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.bitcamp.home.CommandService;

public class MultiUpkloadOkCommand implements CommandService {

	@Override
	public String pocessStart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 저장할위치
		String path = req.getServletContext().getRealPath("/upload");
		try {
			//1.팩토리 객체생성
			DiskFileItemFactory dfif = new DiskFileItemFactory();
			//2. 팩토리에다가 업로드 위치 셋팅
			File f = new File(path);
			dfif.setRepository(f);
			
			//3.servletFileUpload객체생성
			ServletFileUpload fileUpload = new ServletFileUpload(dfif);
			
			//4. 폼의 필드수 만큼 fileitem을 구할수 있다.
			List<FileItem> items= fileUpload.parseRequest(req); 
			System.out.println("items.size() ======= >  " + items.size());
			
			DataVO vo = new DataVO();
			for(FileItem item: items) {
				//텍스트 필드인지 첨부파일 파일인지
				if(item.isFormField()) {//제목 글내용
					String fieldName = item.getFieldName();//필드명
					String value = item.getString("UTF-8");
					
					if(fieldName.equals("title")) {vo.setTitle(value);}
					if(fieldName.equals("content")) {vo.setContent(value);}
				}else { //파일
					//파일의 크기를 구하여 0보다 크면 업로드 구현
					if(item.getSize()>0) { //getSize()->파일크기
						String oriFilename = item.getName();//원래파일명
						
						//////////////////////////////////////////중복일때 이름 늘려서처리하기 aaaa.gif -> aaaa_1.gif -> aaaa_2.gif
						int dot= oriFilename.lastIndexOf("."); //.의 위치
						String filename = oriFilename.substring(0, dot);
						String ext = oriFilename.substring(dot+1);
						
						File file = new File(path, oriFilename);
						int idx=1;
						while(file.exists()) { //파일이 있으면 true 없으면 :false;
							file = new File(path, filename+"_"+ idx++ +"."+ext);
						}
						
						//업로드 실행
						item.write(file);
					}//if
				}//if
			}//for
			System.out.println("제목 => " + vo.getTitle());
			System.out.println("글내용 => " + vo.getContent());
			
		} catch (Exception e) {
			System.out.println("업로드 에러 ==> " + e.getMessage());
		}
		return "/index.jsp";
	}

}
