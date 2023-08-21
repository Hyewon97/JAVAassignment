package ems.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ems.dao.EmsUserDAO;
import ems.model.EmsUser;


// 하나의 서블릿에서 관리하기 위해 "/" 을 사용
@WebServlet("/")
public class EmsUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmsUserDAO emsUserDAO;

	public void init() {
		emsUserDAO = new EmsUserDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertUser(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateUser(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	// showFrom
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}
	
	// insert
		private void insertUser(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String department = request.getParameter("department");
			EmsUser newEmsUser = new EmsUser(name, email, department);
			emsUserDAO.insertUser(newEmsUser);
			response.sendRedirect("list");
		}
		
	// deleteForm
		private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int empNum = Integer.parseInt(request.getParameter("empNum"));
			emsUserDAO.deleteUser(empNum);
			response.sendRedirect("list");
		}
		
	
	// showEditForm
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int empNum = Integer.parseInt(request.getParameter("empNum"));
		EmsUser existingUser = emsUserDAO.selectUser(empNum);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("emsUser", existingUser);
		dispatcher.forward(request, response);

	}
	
	// updateForm
	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int empNum = Integer.parseInt(request.getParameter("empNum"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String department = request.getParameter("department");

		EmsUser emsUser = new EmsUser(empNum, name, email, department);
		emsUserDAO.updateUser(emsUser);
		response.sendRedirect("list");
	}
		
	// listUser
	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<EmsUser> listUser = emsUserDAO.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}
	

}










