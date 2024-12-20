package controller;
import model.DBConnect;
import model.project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
/**
 * Servlet implementation class GioiThieu
 */
@WebServlet("/GioiThieu")
public class GioiThieu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GioiThieu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
List<Map<String, String>> giangVienList = new ArrayList<>();
        
        // Truy vấn cơ sở dữ liệu để lấy thông tin giảng viên
        try (Connection conn = DBConnect.getConnection()) {
            String sql = "SELECT HoTen, QueQuan, NgaySinh, ChuyenNganh, SDT FROM GiangVien";
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    // Lưu trữ thông tin giảng viên vào danh sách
                    Map<String, String> giangVien = new HashMap<>();
                    giangVien.put("HoTen", rs.getString("HoTen"));
                    giangVien.put("QueQuan", rs.getString("QueQuan"));
                    giangVien.put("NgaySinh", rs.getString("NgaySinh"));
                    giangVien.put("ChuyenNganh", rs.getString("ChuyenNganh"));
                    giangVien.put("SDT", rs.getString("SDT"));
                    giangVienList.add(giangVien);
                }

                // Đặt danh sách giảng viên vào thuộc tính yêu cầu để truyền tới JSP
                request.setAttribute("giangVienList", giangVienList);
                request.getRequestDispatcher("giangVienList.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Lỗi khi tải danh sách giảng viên: " + e.getMessage());
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}