package depRegi.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import empRegi.dao.EmployeeDao;
import empRegi.model.Employee;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/register") // 매핑 주소
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private EmployeeDao employeeDao = new EmployeeDao();
	
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/employeeregister.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String empNum = request.getParameter("empNum"); // 사원번호
		String name = request.getParameter("name"); // 이름
		String email = request.getParameter("email"); // 이메일
		String password = request.getParameter("password"); // 비밀번호
		String hireDate = request.getParameter("hireDate"); // 입사일
		
		Employee employee = new Employee();
		employee.setEmpNum(Integer.parseInt(empNum)); // 스트링 값을 int로 변환
		employee.setName(name);
		employee.setEmail(email);
		employee.setPassword(password);
		employee.setHireDate(hireDate);
		
		try {
			employeeDao.registerEmployee(employee);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// 리다이렉트
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/employeedetails.jsp");
		dispatcher.forward(request, response);
	
	}

}










