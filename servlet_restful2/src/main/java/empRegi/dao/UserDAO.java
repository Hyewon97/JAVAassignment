package empRegi.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import empRegi.model.User;

public class UserDAO {

    String url = "jdbc:sqlserver://192.168.50.107:1433;databaseName=hw_db;encrypt=true;trustServerCertificate=true;characterEncoding=UTF-8;serverTimezone=UTC";

    String INSERT_USERS_SQL = "insert into EmsUsers (name, email, department) " + "values(?, ?, ?)";
    String SELECT_ALL_USERS = "select * from EmsUsers";
    String DELETE_USERS_SQL = "delete from EmsUsers where empNum = ?";
    String SELECT_USER_BY_empNum = "select empNum, name, email, department from EmsUsers where empNum=?";
    String UPDATE_USERS_SQL = "update EmsUsers set name = ?,email= ?, department =? where empNum = ?";

    public UserDAO() {
    }

    // db 연결
    protected Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, "phw", "1111");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    // 새 사용자를 데이터베이스에 삽입
    public void insertUser(User user) throws SQLException {
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getDepartment());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    // 특정 사용자를 db에서 조회
    public User selectUser(int empNum) {
        User user = null;
        
        // db 연동
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_empNum);) {
            preparedStatement.setInt(1, empNum);
            
            // 쿼리 실행
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String department = rs.getString("department");
                user = new User(empNum, name, email, department);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    // 모든 사용자 정보를 db에서 조회하여 리스트로 반환
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int empNum = rs.getInt("empNum");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String department = rs.getString("department");
                users.add(new User(empNum, name, email, department));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    // 특정 사용자를 db에서 삭제
    public boolean deleteUser(int empNum) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, empNum);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getDepartment());
            statement.setInt(4, user.getEmpNum());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    // 새로운 메서드: 사용자 정보 생성
    public void createUser(User user) throws SQLException {
        insertUser(user);
    }

    // 새로운 메서드: 사용자 정보 수정
    public boolean modifyUser(User user) throws SQLException {
        return updateUser(user);
    }
}
