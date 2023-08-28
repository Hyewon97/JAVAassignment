package common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import members.dto.AuthInfo;


public class AuthCheckInterceptor extends HandlerInterceptorAdapter{
	public AuthCheckInterceptor() {
		
	}
	
	
	@Override // 컨트롤러가 수행 되기 전
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 정상적으로 처리 됐으면 t를 리턴, 조건에 맞지 않으면 f를 리턴
		// 인증이 되었는지 확인(로그인)
		// false이면 세션이 없으면 그냥 null이고 
		// true이면 세션이 없으면 세션 생성함
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
			if(authInfo != null) {
				return true;
			}
			
		}
		
		// 환경설정에서 잡아줘야 한다.
		response.sendRedirect(request.getContextPath() + "/member/login.do");
		return false;
		
		
	
	}
	
	
	@Override // 컨트롤러가 수행 된 후
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		super.postHandle(request, response, handler, modelAndView);
	}
	
	
	@Override // 컨트롤러가 수행 된 후 뷰 페이지를 응답하기 진전
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		super.afterCompletion(request, response, handler, ex);
	}
	
} // end class
