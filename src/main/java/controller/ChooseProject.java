package controller;

import model.DBConnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/ChooseProject")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class ChooseProject extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ChooseProject() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = DBConnect.getConnection()) {
            String sql = """
                SELECT 
                    p.idProject, 
                    p.title, 
                    p.description, 
                    admin.username AS teacherName
                FROM projects p
                LEFT JOIN users admin ON p.admin_id = admin.idUser
            """;

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();

                List<Map<String, String>> projects = new ArrayList<>();
                while (rs.next()) {
                    Map<String, String> project = new HashMap<>();
                    project.put("idProject", rs.getString("idProject"));
                    project.put("title", rs.getString("title"));
                    project.put("description", rs.getString("description"));
                    project.put("teacherName", rs.getString("teacherName"));
                    projects.add(project);
                }

                request.setAttribute("projects", projects);
                request.getRequestDispatcher("guestProject.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Lỗi khi tải danh sách đề tài: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int guestId = (int) request.getSession().getAttribute("guestId");
        try (Connection conn = DBConnect.getConnection()) {
            switch (action) {
                case "register": // Đăng ký đề tài mới
                    int projectId = Integer.parseInt(request.getParameter("project_id"));
                    String sqlRegister = """
                        INSERT INTO project_registrations (project_id, guest_id)
                        VALUES (?, ?)
                        ON DUPLICATE KEY UPDATE project_id = VALUES(project_id)
                    """;
                    try (PreparedStatement ps = conn.prepareStatement(sqlRegister)) {
                        ps.setInt(1, projectId);
                        ps.setInt(2, guestId);
                        ps.executeUpdate();
                        
                        request.setAttribute("message", "success");
                        request.setAttribute("name", "Thêm dề tài");
                        request.getRequestDispatcher("guestProject.jsp").forward(request, response);
                    }
                    break;

                case "change": // Xóa đề tài đã đăng ký
                    // Kiểm tra xem đã có đề tài nào được đăng ký chưa
                    String checkSql = "SELECT COUNT(*) FROM project_registrations WHERE guest_id = ?";
                    try (PreparedStatement checkPs = conn.prepareStatement(checkSql)) {
                        checkPs.setInt(1, guestId);
                        ResultSet rs = checkPs.executeQuery();
                        if (rs.next() && rs.getInt(1) == 0) {
                            response.getWriter().write("Không có đề tài nào để xóa!");
                            return;
                        }
                    }

                    // Xóa đề tài
                    String sqlDelete = "DELETE FROM project_registrations WHERE guest_id = ?";
                    try (PreparedStatement ps = conn.prepareStatement(sqlDelete)) {
                        ps.setInt(1, guestId); // Xóa dựa trên guest_id
                        ps.executeUpdate();
                    }
                    
                            

                        // Lấy danh sách các đề tài
                        String sql = """
                                SELECT 
                                    p.idProject, 
                                    p.title, 
                                    p.description, 
                                    admin.username AS teacherName
                                FROM projects p
                                LEFT JOIN users admin ON p.admin_id = admin.idUser
                            """;

                        try (PreparedStatement ps = conn.prepareStatement(sql)) {
                            ResultSet rs = ps.executeQuery();

                            List<Map<String, String>> projects = new ArrayList<>();
                            while (rs.next()) {
                                Map<String, String> project = new HashMap<>();
                                project.put("idProject", rs.getString("idProject"));
                                project.put("title", rs.getString("title"));
                                project.put("description", rs.getString("description"));
                                project.put("teacherName", rs.getString("teacherName"));
                                projects.add(project);
                            }

                            request.setAttribute("projects", projects);
                            request.setAttribute("message", "success");
                            request.setAttribute("name", "Xóa dề tài");
                            request.getRequestDispatcher("guestProject.jsp").forward(request, response);
                    }
                    break;
        
                	
                case "submit": // Nộp bài
                    Part filePart = request.getPart("file");
                    if (filePart != null && filePart.getSize() > 0) {
                    	String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    	String uploadDir = getServletContext().getRealPath("") + "uploads";
                    	File uploadFolder = new File(uploadDir);

                        if (!uploadFolder.exists()) {
                            uploadFolder.mkdir();
                        }

                        String filePath = uploadDir + File.separator + fileName;
                        filePart.write(filePath);

                        String sqlSubmit = """
                            UPDATE project_registrations
                            SET submitted_file = ?, submit_time = NOW()
                            WHERE guest_id = ?
                        """;
                        try (PreparedStatement ps = conn.prepareStatement(sqlSubmit)) {
                            ps.setString(1, fileName);
                            ps.setInt(2, guestId);
                            ps.executeUpdate();
                        }
                            String sql1 = """
                                    SELECT 
                                        p.idProject, 
                                        p.title, 
                                        p.description, 
                                        admin.username AS teacherName
                                    FROM projects p
                                    LEFT JOIN users admin ON p.admin_id = admin.idUser
                                """;

                                try (PreparedStatement ps = conn.prepareStatement(sql1)) {
                                    ResultSet rs = ps.executeQuery();

                                    List<Map<String, String>> projects = new ArrayList<>();
                                    while (rs.next()) {
                                        Map<String, String> project = new HashMap<>();
                                        project.put("idProject", rs.getString("idProject"));
                                        project.put("title", rs.getString("title"));
                                        project.put("description", rs.getString("description"));
                                        project.put("teacherName", rs.getString("teacherName"));
                                        projects.add(project);
                                    }
                            request.setAttribute("projects", projects);
                            request.setAttribute("message", "success");
                            request.setAttribute("name", "Nộp dề tài");
                            request.getRequestDispatcher("guestProject.jsp").forward(request, response);
                        }
                        break;
                    } 
                    break;
            }

            response.sendRedirect("ChooseProject");
        } catch (Exception e) {
        	 e.printStackTrace();
             request.setAttribute("message", "error");
             request.setAttribute("name", "Hành động");
        }
    }
    
}
