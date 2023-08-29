package emp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import emp.dto.PageDTO;
import emp.service.EmpService;

// 컨트롤러 선언
@Controller
public class EmpController {
	
	private EmpService empService;
	private PageDTO pdto; // 페이징 처리
	
	public EmpController() {
		
	}
	
	// 서비스 set하기
	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}
	
	// 리스트 보여주기
	@RequestMapping("/emp/list.do")
	public ModelAndView listExecute(@ModelAttribute("pv") PageDTO pv, ModelAndView mav) {
		int totalRecord = empService.countProcess();

		if (totalRecord >= 1) {
			if (pv.getCurrentPage() == 0)
				pv.setCurrentPage(1); // 현재 페이지 1을 넣어줌
			this.pdto = new PageDTO(pv.getCurrentPage(), totalRecord);
			mav.addObject("pv", this.pdto);
			mav.addObject("aList", empService.listProcess(this.pdto));

		}
		mav.setViewName("emp/list");
		return mav;
	}

}
