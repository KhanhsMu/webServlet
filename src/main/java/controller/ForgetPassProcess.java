package controller;
import model.DBConnect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ForgetPassProcess")
public class ForgetPassProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ForgetPassProcess() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String email = request.getParameter("email");

	    try (Connection conn = DBConnect.getConnection()) {
	        // Kiểm tra email trong cơ sở dữ liệu và lấy thông tin tài khoản
	        String query = "SELECT nameRegis, passRegis FROM registerweb WHERE gmailRegis = ?";
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setString(1, email);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            String username = rs.getString("nameRegis");
	            String password = rs.getString("passRegis");

	            // Gửi email
	            sendEmail(email, username, password);

	            response.getWriter().write("Mật khẩu đã được gửi đến email của bạn!");
	        } else {
	            response.getWriter().write("Email không tồn tại trong hệ thống!");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.getWriter().write("Lỗi khi xử lý yêu cầu quên mật khẩu!");
	    }
	}
	private void sendEmail(String toEmail, String username, String password) throws MessagingException {
	    final String fromEmail = "btrankhanhminh@gmail.com"; // Thay bằng email của bạn
	    final String emailPassword = "reuk exsi bevd qlbc"; // Thay bằng mật khẩu email
	    
	    // Cấu hình SMTP
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");

	    // Tạo session
	    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(fromEmail, emailPassword);
	        }
	    });

	    // Tạo nội dung email
	    Message message = new MimeMessage(session);
	    message.setFrom(new InternetAddress(fromEmail));
	    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
	    message.setSubject("Khôi phục mật khẩu");
	    message.setText("Xin chào " + username + ",\n\n"
	            + "Mật khẩu của bạn là: " + password + "\n\n"
	            + "Vui lòng không chia sẻ mật khẩu này với bất kỳ ai.\n\n"
	            + "Trân trọng,\nĐội ngũ hỗ trợ.");

	    // Gửi email
	    Transport.send(message);
	}


}
