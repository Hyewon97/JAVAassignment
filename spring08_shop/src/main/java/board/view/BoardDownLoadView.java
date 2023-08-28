package board.view;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import board.dao.BoardDAO;
import common.file.FileUpload;

// 다운로드 창을 띄우기 위한 뷰 페이지
public class BoardDownLoadView extends AbstractView {
	private BoardDAO boardDao;

	public BoardDownLoadView() {

	}

	public void setBoardDao(BoardDAO boardDao) {
		this.boardDao = boardDao;
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			
		int num= Integer.parseInt(request.getParameter("num"));
		String saveDirectory = FileUpload.urlPath(request); // 파일이 저장되어 있는 웹 서버 위치를 가져온다.
		
		String upload = boardDao.getFile(num);
		
		// 난수_실제파일명. 난수_는 다운 받을 때 출력할 필요가 없음. 제거 필요
		String fileName = upload.substring(upload.indexOf("_")+1); // _ 다음부터 이름을 가져온다.
		
		//파일명이 한글일때 인코딩 작업을 한다.
		String str = URLEncoder.encode(fileName,"UTF-8");
		
		//원본파일명에서 공백이 있을 때, +표시가 되므로 공백으로 처리해줌.
		str = str.replaceAll("\\+", "%20"); // %20은 공백을 의미
		
		// 타입이 정확하지 않고 다양할 때 아래처럼 선언한다.
		// 컨텐츠 타입
		response.setContentType("application/octet-stream");
		
		// 다운로드 창에 보여줄 파일명을 지정한다.
		response.setHeader("Content-Disposition", "attachment;filename=" + str +";");
		
		// 서버에 저장된 파일을 읽어와 클라이언트에 출력해 준다.                  완전한 파일명을 넣어야 한다.
		FileCopyUtils.copy(new FileInputStream(new File(saveDirectory, upload)), response.getOutputStream());
	}

}











