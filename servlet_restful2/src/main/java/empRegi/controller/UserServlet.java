package empRegi.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import empRegi.dao.UserDAO;
import empRegi.model.User;

@WebServlet("/users/*")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;
    private Gson gson;

    public void init() { // 인스턴스 초기화
        userDAO = new UserDAO();
        gson = new Gson();
    }

    // post 요청
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String resourcePath = requestURI.substring(request.getContextPath().length() + "/users".length());

        try {
            if (resourcePath.isEmpty() || resourcePath.equals("/")) {
                createUser(request, response);
            } else if (resourcePath.matches("/\\d+")) {
                int empNum = Integer.parseInt(resourcePath.substring(1));
                updateUser(request, response, empNum);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().print("Endpoint not found.");
            }
        } catch (SQLException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().print("Internal Server Error");
        }
    }

    // get 요청, 모든 사용자를 조회하거나 특정 사용자를 조회
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String resourcePath = requestURI.substring(request.getContextPath().length() + "/users".length());

        try {
            if (resourcePath.isEmpty() || resourcePath.equals("/")) {
                listUsers(request, response);
            } else if (resourcePath.matches("/\\d+")) {
                int empNum = Integer.parseInt(resourcePath.substring(1));
                getUser(request, response, empNum);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().print("Endpoint not found.");
            }
        } catch (SQLException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().print("Internal Server Error");
        }
    }

    // 새 사용자 생성
    private void createUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String requestBody = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
        User newUser = gson.fromJson(requestBody, User.class);
        userDAO.insertUser(newUser);
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.getWriter().print("User created successfully.");
    }

    // 모든 사용자 조회, json 형식으로 반환
    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        List<User> listUser = userDAO.selectAllUsers();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(listUser));
    }

    // 특정 사용자 조회, json 형식으로 반환
    private void getUser(HttpServletRequest request, HttpServletResponse response, int empNum)
            throws SQLException, IOException {
        User user = userDAO.selectUser(empNum);
        if (user != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gson.toJson(user));
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().print("User not found.");
        }
    }

    // 특정 사용자 정보 수정
    private void updateUser(HttpServletRequest request, HttpServletResponse response, int empNum)
            throws ServletException, IOException, SQLException {
        String requestBody = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
        User updatedUser = gson.fromJson(requestBody, User.class);
        updatedUser.setEmpNum(empNum);
        boolean success = userDAO.updateUser(updatedUser);
        if (success) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().print("User updated successfully.");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().print("User not found or update failed.");
        }
    }

    private void getUser(HttpServletRequest request, HttpServletResponse response, int empNum)
            throws SQLException, IOException {
        User user = userDAO.selectUser(empNum);
        if (user != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gson.toJson(user));
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().print("User not found.");
        }
        response.getWriter().close();
    }
}
