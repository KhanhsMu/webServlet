package controller;
import model.DBConnect;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginProcess")
public class LoginProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginProcess() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");

	    try (Connection conn = DBConnect.getConnection()) {
	        String sql = "SELECT role FROM registerweb WHERE nameRegis = ? AND passRegis = ?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, username);
	        ps.setString(2, password); // Mã hóa mật khẩu nếu cần
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            String role = rs.getString("role");

	            HttpSession session = request.getSession();
	            session.setAttribute("username", username); // Lưu thông tin vào session
	            session.setAttribute("role", role); // Lưu vai trò vào session

	            // Phân quyền theo vai trò
	            if ("admin".equalsIgnoreCase(role)) {
	                response.sendRedirect("adminHome.jsp"); // Trang admin
	            } else if ("guest".equalsIgnoreCase(role)) {
	                response.sendRedirect("guestHome.jsp"); // Trang khách
	            } 
	        } else {
	            response.getWriter().write("Sai tài khoản hoặc mật khẩu!");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.getWriter().write("Lỗi kết nối cơ sở dữ liệu!");
	    }
	}

}