package ems.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ems.dao.EmsUserDao;

// 하나의 서블릿에서 관리하기 위해 "/" 을 사용
@WebServlet("/")
public class EmsUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmsUserDao emsUserDAO;

	public void EmsUserServlet() {
		this.emsUserDAO = new EmsUserDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		switch (action) {
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":

			break;
		case "/delete":

			break;
		case "/edit":

			break;
		case "/update":

			break;
		default:

			break;
		}
	}
	
	// showFrom
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

}










