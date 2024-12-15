package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnect {
	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/web?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	    String username = "root";
	    String password = "10062004";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver"); // Đăng ký driver
	        return DriverManager.getConnection(url, username, password);
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	public static void main(String[] args) {
        try {
            Connection conn = DBConnect.getConnection();
            if (conn != null) {                
                System.out.println("Connected!");
            } else {
                System.out.println("Fail!");
            }
        } catch (Exception e) {
        }
    }
}

