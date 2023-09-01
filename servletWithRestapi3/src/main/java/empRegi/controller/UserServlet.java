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

import com.google.gson.Gson;

import empRegi.dao.UserDAO;
import empRegi.model.User;

@WebServlet("/users/*") // 매핑 주소 / > users로 변경, users/{empNum} 이렇게 사용하도록
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO userDAO = new UserDAO();
	private Gson gson = new Gson(); // json 라이브러리 사용. java Object <-> Json

	// post, 사용자 새로 등록
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI(); // URI 요청, 문자열로 저장
		String resourcePath = requestURI.substring(request.getContextPath().length() + "/users".length()); // 가져온 URI에서
																											// (프로젝트
																											// Path +
																											// /users)
																											// 부터 끝까지
																											// 잘라서 문자열
																											// resourcePath에
																											// 저장

		try {
			if (resourcePath.isEmpty() || resourcePath.equals("/")) { // resourcePath가 없거나 '/'면
				createUser(request, response); // 새로운 사용자 등록 // 프소트맨 테스트 > http://localhost:8090/servlet/users/
												// {"name":"테테테테","email":"test@2naver.com","department":"경영"}
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 에러인 경우
				response.getWriter().print("Endpoint not found."); // 에러 메세지 출력
			}
		} catch (SQLException ex) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 에러인 경우
			response.getWriter().print("Internal Server Error"); // 에러 메세지 출력
		}
	}

	// get, (전체, 선택) 사용자 조회
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String resourcePath = requestURI.substring(request.getContextPath().length() + "/users".length());

		try {
			if (resourcePath.isEmpty() || resourcePath.equals("/")) {
				listUsers(request, response); // 모든 사용자 조회 // 포스트맨 테스트 > http://localhost:8090/servlet/users/

				// forward
				/*
				 * RequestDispatcher dispatcher =
				 * request.getRequestDispatcher("/WEB-INF/views/user-list.jsp");
				 * dispatcher.forward(request, response);
				 */

			} else if (resourcePath.matches("/\\d+")) { // 변호가 있으면
				int empNum = Integer.parseInt(resourcePath.substring(1));
				getUser(request, response, empNum); // 특정 사용자 조회 // 포스트맨 테스트 > http://localhost:8090/servlet/users/ 48

				// forward
				/*
				 * RequestDispatcher dispatcher =
				 * request.getRequestDispatcher("/WEB-INF/views/user-form.jsp");
				 * dispatcher.forward(request, response);
				 */

			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().print("Endpoint not found.");
			}
		} catch (SQLException ex) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().print("Internal Server Error");
		}
	}

	// Put 요청, 사용자 정보 수정
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		String resourcePath = requestURI.substring(request.getContextPath().length() + "/users".length());

		try {
			if (resourcePath.matches("/\\d+")) {
				int empNum = Integer.parseInt(resourcePath.substring(1));
				updateUser(request, response, empNum); // 특정 사용자 정보 수정 // 포스트맨 테스트 >
														// http://localhost:8090/servlet/users/67
														// {"name":"수정","email":"test@2naver.com","department":"경영"}
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().print("Endpoint not found.");
			}
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().print("Internal Server Error");
		}

	}

	// doDelete
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String resourcePath = requestURI.substring(request.getContextPath().length() + "/users".length());

		if (resourcePath.matches("/\\d+")) {
			int empNum = Integer.parseInt(resourcePath.substring(1));

			try {
				boolean success = userDAO.deleteUser(empNum);
				if (success) {
					response.getWriter().print("User deleted successfully.");
					response.setStatus(HttpServletResponse.SC_OK);
				} else {
					response.getWriter().print("User not found or delete failed.");
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				}
			} catch (SQLException ex) {
				response.getWriter().print("Internal Server Error");
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().print("Endpoint not found.");
		}
	}

	// 새 사용자 생성
	private void createUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String requestBody = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual); // 이
																													// 부분
																													// 잘
																													// 해석이
																													// 안되는데
		User newUser = gson.fromJson(requestBody, User.class);
		userDAO.insertUser(newUser);
		response.setStatus(HttpServletResponse.SC_CREATED);
		response.getWriter().print("User created successfully.");
	}

	// 모든 사용자 조회, json 형식으로 반환
	private void listUsers(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		List<User> listUser = userDAO.selectAllUsers(); // DB에 있는 모든 사용자들의 정보를 List에 넣음
		response.setContentType("application/json"); // HttpServletResponse 객체로 Json 데이터 반환
		response.setCharacterEncoding("UTF-8"); // UTF-8로 인코딩
		response.getWriter().write(gson.toJson(listUser)); // listUser를 object->Json으로 매핑
	}

	// 특정 사용자 조회, json 형식으로 반환
	private void getUser(HttpServletRequest request, HttpServletResponse response, int empNum)
			throws SQLException, IOException {
		User user = userDAO.selectUser(empNum); // emmpNum에 해당하는 사용자의 정보를 user에 저장
		if (user != null) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(gson.toJson(user));
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 에러
			response.getWriter().print("User not found."); // 에러 메세지 출력
		}
	}

	// 특정 사용자 정보 수정
	private void updateUser(HttpServletRequest request, HttpServletResponse response, int empNum)
			throws ServletException, IOException, SQLException {
		String requestBody = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
		User updatedUser = gson.fromJson(requestBody, User.class); // json -> Object로 매핑
		updatedUser.setEmpNum(empNum);

		boolean success = userDAO.updateUser(updatedUser);
		if (success) { // true면
			response.setStatus(HttpServletResponse.SC_OK); // 200
			response.getWriter().print("User updated successfully."); // 성공했다고 출력
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 에러
			response.getWriter().print("User not found or update failed."); // 에러 메세지 출력
		}
	}

}
