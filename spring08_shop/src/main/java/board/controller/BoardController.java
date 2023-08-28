package board.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import board.dto.BoardDTO;
import board.dto.PageDTO;
import board.service.BoardService;
import common.file.FileUpload;
import members.dto.AuthInfo;

@Controller
public class BoardController {

	private BoardService boardService;
	private PageDTO pdto;

	public BoardController() {

	}

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	@RequestMapping("/board/list.do")
	public ModelAndView listExecute(@ModelAttribute("pv") PageDTO pv, ModelAndView mav) {
		int totalRecord = boardService.countProcess();

		if (totalRecord >= 1) {
			if (pv.getCurrentPage() == 0)
				pv.setCurrentPage(1); // 현재 페이지 1을 넣어줌
			this.pdto = new PageDTO(pv.getCurrentPage(), totalRecord);
			mav.addObject("pv", this.pdto);
			mav.addObject("aList", boardService.listProcess(this.pdto));

		}
		mav.setViewName("board/list");
		return mav;
	}

	@RequestMapping(value = "/board/write.do", method = RequestMethod.GET)
	public ModelAndView wrtieExecute(@ModelAttribute("dto") BoardDTO dto, @ModelAttribute("pv") PageDTO pv,
			ModelAndView mav) {
		// 답변글일 때

		mav.setViewName("board/write");
		return mav;
	}

	// 저장버튼 눌렀을때 여기서 처리를 해준다.
	@RequestMapping(value = "/board/write.do", method = RequestMethod.POST)
	public String writeProExcute(BoardDTO dto, PageDTO pv, HttpServletRequest req, HttpSession session, RedirectAttributes ratt) {
		// 1. 첨부파일 처리하기, 현재 멀티파일에 값이 있는지 없는지 확인하기. 첨부파일 유뮤 체크
		MultipartFile file = dto.getFilename();
		
								// null 값이여서 값을 안가지고 온다.
		//System.out.println(dto.getMembersDTO().getMemberName());

		// 첨부파일이 비어있지 않으면.. 파일 첨부가 있으면
		if (!file.isEmpty()) {
			UUID random = FileUpload.saveCopyFile(file, req);
			dto.setUpload(random + "_" + file.getOriginalFilename());
		}

		dto.setIp(req.getRemoteAddr()); // 클라이언트에 ip주소를 저장한다.

		// 본문 값드을 나오기 위해서.. 이거 안적으면 null로 저장이 된다. 아니 근데 sql에서는 본문 값이 저장되긴 하던데???
		// write로 넘어오면.. 뭐가 안맞다고.. 뭐가 안맞죠
		//AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
     	//dto.setMemberEmail(authInfo.getMemberEmail());
		
		boardService.insertProcess(dto); // 제목글에 대해서
		
		
		// 답변글일때 현재 페이지 값을 넘겨준다.
		if(dto.getRef()!=0) {
			ratt.addAttribute("currentPage",pv.getCurrentPage());
		}
		
		return "redirect:/board/list.do";
	}

	@RequestMapping("/board/view.do")
	public ModelAndView viewExecute(int currentPage, int num, ModelAndView mav) {
		System.out.printf("currentPage:%d, num:%d\n", currentPage, num);
		mav.addObject("dto", boardService.contentProcess(num));
		mav.addObject("currentPage", currentPage);
		mav.setViewName("board/view");
		return mav;
	}

	@RequestMapping(value = "/board/update.do", method = RequestMethod.GET)
	public ModelAndView updateExecute(int num, int currentPage, ModelAndView mav) {
		mav.addObject("dto", boardService.updateSelectProcess(num));
		mav.addObject("currentPage", currentPage);
		mav.setViewName("board/update");
		return mav;
	}

	// dto로 받은 값을 view 페이지에 사용할 때, 값을 저장하지 않을 때 이렇게 선언하면 바로 사용 가능하다.
	// public String updateExecute(@ModelAttribute("dto") BoardDTO dto, int
	// currentPage) {
	@RequestMapping(value = "/board/update.do", method = RequestMethod.POST)
	//public String updateExecute(BoardDTO dto, int currentPage, HttpServletRequest request) {
	public String updateExecute(BoardDTO dto, int currentPage, HttpServletRequest request, RedirectAttributes ratt) {	
		// 서버 정보를 가져온다.
		MultipartFile file = dto.getFilename();
		if (!file.isEmpty()) {
			UUID random = FileUpload.saveCopyFile(file, request);
			dto.setUpload(random + "_" + file.getOriginalFilename());
		}

		boardService.updateProcess(dto, FileUpload.urlPath(request));
		
		// 다른 방법으로 넘기는 방법을 하겠어요.
		// return "redirect:/board/list.do?currentPage=" + currentPage;
	
		//primitive 값만 가능, url 주소에 노출된다.(currentPage가 나옴.. 왜? get 방식이여서)
		ratt.addAttribute("currentPage", currentPage);
		
		//object 값 가능 (session에 담아서 넘겨줌), url 주소에 노출이 안된다. post랑은 상관없이 seesion에 담겨있어서 그럼
		// session에 담아서 넘겨주면 list.do에 가는데 이거는 자동으로 없어져서 그런다고... 오키!
//		ratt.addFlashAttribute("dto",dto);
		
		// 위 기능으로 테스트 하려면 아래 코드를 사용해야한다. 위 코드는 실행이 안될꺼
		//ratt.addFlashAttribute("currentPage",currentPage);
		return "redirect:/board/list.do"; // 리스트를 호출.
	}
	
	@RequestMapping("/board/delete.do")
	public String deleteExecute(int num, int currentPage, HttpServletRequest request, RedirectAttributes ratt) {
		ratt.addAttribute("currentPage", currentPage);
		boardService.deleteProcess(num, FileUpload.urlPath(request));
		return "redirect:/board/list.do"; 
	}
	
	@RequestMapping("/board/contentdownload.do")
	public ModelAndView downloadExecute(int num, ModelAndView mav) {
		mav.addObject("num", num);
		mav.setViewName("download");
		return mav;
		
	}
} // end class








