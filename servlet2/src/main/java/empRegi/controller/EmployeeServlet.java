package empRegi.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import empRegi.dao.UserDAO;
import empRegi.model.Employee;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/") // 매핑 주소
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO employeeDao = new UserDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/employeeregister.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String name = request.getParameter("name"); // 이름
		String email = request.getParameter("email"); // 이메일
		String department = request.getParameter("department"); // 이메일
		
		
		Employee employee = new Employee();
		employee.setName(name);
		employee.setEmail(email);
		employee.setDepartment(department);
		
		try {
			employeeDao.registerEmployee(employee);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// 리다이렉트
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/employeedetails.jsp");
		dispatcher.forward(request, response);
		 
		
	}
	
	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		List<Employee> listUser = employeeDao.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/employeedetails.jsp");
		dispatcher.forward(request, response);
		
	}



}
