package com.bitcamp.home;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/*.do")
public class ControllerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//매핑 주소와 실행할 Command객체를 보관할 맵
	HashMap<String,CommandService> map = new HashMap<String, CommandService>();
	
    public ControllerAction() {
       super();
    }

	public void init(ServletConfig config) throws ServletException {
		//properties파일명을 web.xml에서 가져오기
		String propertiesFilename =config.getInitParameter("proConfig"); //web.xml에서 이름이 "proConfig"인 것을 가져오는것(getInitParamter)
		
		//Map계열의 컬렉션 프레임워크와 비슷하게 동작하는파일  https://codevang.tistory.com/163
		Properties prop = new Properties(); //key:String, value:String
		try {
			FileInputStream fis = new FileInputStream(propertiesFilename); //urlMapping.properties에 안에있는 값을 가져와서 읽는것
			
			//urlMapping.properties파일의 내용을 읽어와 properties객체로 대입한다.
			prop.load(fis); //읽는 메소드
		} catch (Exception e) {
			System.out.println("프로퍼티 객체 생성 에러 발생 ==> " + e.getMessage());
		}
		///////////////////////////////////////////////////////////////////////
		try {
			//properties의 키목록 구하기
			Enumeration keyList = prop.propertyNames();
			
			while(keyList.hasMoreElements()) {
				//Key에 대한 커멘드 클래스명을 가져온다
				String key = (String)keyList.nextElement();
				String commandName = prop.getProperty(key);
				System.out.println(key +" => "+ commandName);
				
				//문자열을 객체로 생성하여 Map추가
				Class classObject = Class.forName(commandName);
				//														 getDeclaredConstructor 메소드는 public, protected, private, default 상관없이 class 안의 모든 생성자에 접근 가능함
				CommandService services = (CommandService) classObject.getDeclaredConstructors()[0].newInstance();
				map.put(key, services);
				
			}
		}catch(Exception e){
			System.out.println("프로퍼티 내용을 맵 객체로 변환 에러 --> " + e.getMessage());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//접속자의 url주소를 알아낸다.
		String uri = request.getRequestURI(); // /WebMVC/index.do
		String ctx = request.getContextPath(); // /WebMVC
		System.out.println("uri -> " + uri);
		System.out.println("ctx => " + ctx);
		
		String urlMapping = uri.substring(ctx.length()); // /index.do
		
		CommandService command = map.get(urlMapping);
		
		String viewFilename = command.pocessStart(request, response);
		
		//뷰파일로 이동하기
		RequestDispatcher dispacher = request.getRequestDispatcher(viewFilename);
		dispacher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
