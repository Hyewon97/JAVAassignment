package accessDB;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class TestWithConfig {
    public static void main(String[] args) {
        try {
            // Load database properties from config file
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream("config/config.properties");
            properties.load(fis);
            fis.close();

            // Get a database connection using the DBConn class
            Connection conn = DBConn.getConnection();
            Statement stmt = conn.createStatement();

            // Read the SQL query from config.properties
            String sqlQuery = properties.getProperty("sql_query");

            ResultSet rs = stmt.executeQuery(sqlQuery);

            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println(name + " " + email);
            }

            // Close resources
            rs.close();
            stmt.close();
            DBConn.closeConnection(); // Close the connection when done
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
