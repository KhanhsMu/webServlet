package controller;
import model.DBConnect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterProcess
 */
@WebServlet("/RegisterProcess")
public class RegisterProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegisterProcess() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection conn = DBConnect.getConnection()) {
            String sql = "INSERT INTO registerweb (gmailRegis, nameRegis, passRegis) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, username);
            ps.setString(3, password); // Mã hóa mật khẩu khi cần
            ps.executeUpdate();
            response.getWriter().write("Đăng ký thành công!");
            response.sendRedirect("Login.jsp"); // Chuyển đến trang đăng nhập
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Lỗi khi đăng ký!");
        }
    }
}


