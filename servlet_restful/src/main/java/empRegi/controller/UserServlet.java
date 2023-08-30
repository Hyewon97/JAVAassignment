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

@WebServlet("/users/*") // 리소스별 매핑 주소
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;
    private Gson gson;

    public void init() {
        userDAO = new UserDAO();
        gson = new Gson();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String resourcePath = requestURI.substring(request.getContextPath().length() + "/users".length());

        try {
            if (resourcePath.isEmpty() || resourcePath.equals("/")) {
                listUsers(request, response);
            } else if (resourcePath.startsWith("/delete/")) {
                int empNum = Integer.parseInt(resourcePath.substring("/delete/".length()));
                deleteUser(request, response, empNum);
            } else if (resourcePath.startsWith("/edit/")) {
                int empNum = Integer.parseInt(resourcePath.substring("/edit/".length()));
                showEditForm(request, response, empNum);
            } else if (resourcePath.startsWith("/update/")) {
                int empNum = Integer.parseInt(resourcePath.substring("/update/".length()));
                updateUser(request, response, empNum);
            } else if (resourcePath.startsWith("/")) {
                int empNum = Integer.parseInt(resourcePath.substring(1));
                getUser(request, response, empNum);
            }
        } catch (SQLException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().print("Internal Server Error");
            response.getWriter().close();
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        List<User> listUser = userDAO.selectAllUsers();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(listUser));
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response, int empNum)
            throws SQLException, IOException {
        boolean success = userDAO.deleteUser(empNum);
        if (success) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().print("User deleted successfully.");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().print("User not found or deletion failed.");
        }
        response.getWriter().close();
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response, int empNum)
            throws SQLException, ServletException, IOException {
        User existingUser = userDAO.selectUser(empNum);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);
    }


    private void updateUser(HttpServletRequest request, HttpServletResponse response, int empNum)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String department = request.getParameter("department");

        User user = new User(empNum, name, email, department);
        userDAO.updateUser(user);
        response.sendRedirect("list");
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
