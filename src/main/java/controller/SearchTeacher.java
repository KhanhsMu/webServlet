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
 * Servlet implementation class SearchTeacher
 */
@WebServlet("/SearchTeacher")
public class SearchTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchTeacher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String search = request.getParameter("search"); // Capture search query
        
        try (Connection conn = DBConnect.getConnection()) {
            String sql = """
            		SELECT 
				        p.idProject, 
				        p.title, 
				        p.description, 
				        admin.username AS teacherName, 
				        u.username AS studentName,
				        pr.submit_time AS submissionTime, 
				        pr.submitted_file AS submittedFile
				    FROM projects p
				    LEFT JOIN users admin ON p.admin_id = admin.idUser
				    LEFT JOIN project_registrations pr ON p.idProject = pr.project_id
				    LEFT JOIN users u ON pr.guest_id = u.idUser
            """;

            if (search != null && !search.trim().isEmpty()) {
                sql += " WHERE admin.username LIKE ?"; // Add condition to filter by teacher name
            }

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                if (search != null && !search.trim().isEmpty()) {
                    ps.setString(1, "%" + search + "%"); // Set the search term with wildcard
                }
                
                ResultSet rs = ps.executeQuery();
                
                List<project> projects = new ArrayList<>();
                while (rs.next()) {
                    project project = new project();
                    project.setIdProject(rs.getInt("idProject"));
                    project.setTitle(rs.getString("title"));
                    project.setDescription(rs.getString("description"));

                    String teacherName = rs.getString("teacherName");
                    String studentName = rs.getString("studentName");
                    Timestamp submissionTime = rs.getTimestamp("submissionTime");
                    String submittedFile = rs.getString("submittedFile");
                    
                    project.setTeacherName(teacherName != null ? teacherName : "Giảng viên chưa được gán");
                    project.setStudentName(studentName != null ? studentName : "Chưa đăng ký");
                    project.setSubmissionTime(submissionTime);
                    project.setSubmittedFile(submittedFile != null ? submittedFile : "Không có file");
                    
                    projects.add(project);
                }
                request.setAttribute("projects", projects);
                request.getRequestDispatcher("adminProject.jsp").forward(request, response);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Lỗi khi tải danh sách! Chi tiết: " + e.getMessage());
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