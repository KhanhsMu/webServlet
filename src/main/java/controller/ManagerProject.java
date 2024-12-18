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


@WebServlet("/ManagerProject")
public class ManagerProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ManagerProject() {
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
				        admin.username AS teacherName, 
				        u.username AS studentName,
				        pr.submit_time AS submissionTime, 
				        pr.submitted_file AS submittedFile
				    FROM projects p
				    LEFT JOIN users admin ON p.admin_id = admin.idUser
				    LEFT JOIN project_registrations pr ON p.idProject = pr.project_id
				    LEFT JOIN users u ON pr.guest_id = u.idUser
            """;

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");


        try (Connection conn = DBConnect.getConnection()) {
            switch (action) {
                case "add":
                    String title = request.getParameter("title");
                    String description = request.getParameter("description");
                    String teacherName = request.getParameter("teacher");

                    String sqlFindTeacher = "SELECT idUser FROM users WHERE username = ? AND role = 'admin'";
                    int teacherId = -1;
                    try (PreparedStatement psFind = conn.prepareStatement(sqlFindTeacher)) {
                        psFind.setString(1, teacherName);
                        ResultSet rsFind = psFind.executeQuery();
                        if (rsFind.next()) {
                            teacherId = rsFind.getInt("idUser");
                        }
                    }
                    String sqlInsert = "INSERT INTO projects (title, description, admin_id) VALUES (?, ?, ?)";
                    try (PreparedStatement psInsert = conn.prepareStatement(sqlInsert)) {
                        psInsert.setString(1, title);
                        psInsert.setString(2, description);
                        psInsert.setInt(3, teacherId);
                        psInsert.executeUpdate();
                    }
                    break;

                case "delete":
                    String idStr = request.getParameter("id");
                    int idDelete = Integer.parseInt(idStr);
                    String sqlDelete = "DELETE FROM projects WHERE idProject = ?";
                    try (PreparedStatement psDelete = conn.prepareStatement(sqlDelete)) {
                        psDelete.setInt(1, idDelete);
                        psDelete.executeUpdate();
                    }
                    break;

                case "editForm": // Hiển thị form chỉnh sửa
                    String idEditStr = request.getParameter("id");
                    if (idEditStr != null && !idEditStr.isEmpty()) {
                        int idProject = Integer.parseInt(idEditStr);

                        String sql = """
                            SELECT p.title, p.description, admin.username AS teacherName
                            FROM projects p
                            LEFT JOIN users admin ON p.admin_id = admin.idUser
                            WHERE p.idProject = ?
                        """;

                        try (PreparedStatement ps = conn.prepareStatement(sql)) {
                            ps.setInt(1, idProject);
                            ResultSet rs = ps.executeQuery();
                            if (rs.next()) {
                                request.setAttribute("id", idProject);
                                request.setAttribute("title", rs.getString("title"));
                                request.setAttribute("description", rs.getString("description"));
                                request.setAttribute("teacherName", rs.getString("teacherName"));
                            }
                        }

                        // Chuyển sang trang editProject.jsp
                        request.getRequestDispatcher("EditProject.jsp").forward(request, response);
                        return;
                    }
                    break;

                case "edit": // Lưu chỉnh sửa
                    String idedit = request.getParameter("id");
                    int idEdit = Integer.parseInt(idedit);
                    String newTitle = request.getParameter("title");
                    String newDescription = request.getParameter("description");
                    String newTeacherName = request.getParameter("teacher");

                    if (newTitle == null || newTitle.trim().isEmpty() ||
                        newDescription == null || newDescription.trim().isEmpty() ||
                        newTeacherName == null || newTeacherName.trim().isEmpty()) {
                        request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin!");
                        doGet(request, response);
                        return;
                    }

                    String sqlFindNewTeacher = "SELECT idUser FROM users WHERE username = ? AND role = 'admin'";
                    int newTeacherId = -1;
                    try (PreparedStatement psFindNew = conn.prepareStatement(sqlFindNewTeacher)) {
                        psFindNew.setString(1, newTeacherName);
                        ResultSet rsFindNew = psFindNew.executeQuery();
                        if (rsFindNew.next()) {
                            newTeacherId = rsFindNew.getInt("idUser");
                        }
                    }

                    String sqlUpdate = "UPDATE projects SET title = ?, description = ?, admin_id = ? WHERE idProject = ?";
                    try (PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {
                        psUpdate.setString(1, newTitle);
                        psUpdate.setString(2, newDescription);
                        psUpdate.setInt(3, newTeacherId != -1 ? newTeacherId : java.sql.Types.NULL);
                        psUpdate.setInt(4, idEdit);
                        psUpdate.executeUpdate();
                    }
                    break;
                case "uploadFile": // Nhận file từ guest
	                    Part filePart = request.getPart("file");
	                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
	                    String uploadDir = getServletContext().getRealPath("") + "uploads";
	                    File uploadFolder = new File(uploadDir);
	
	                    if (!uploadFolder.exists()) {
	                        uploadFolder.mkdir();
	                    }

                        String filePath = uploadDir + File.separator + fileName;
                        filePart.write(filePath);

                        int guestId = Integer.parseInt(request.getParameter("guestId")); // Lấy guestId
                        int projectId = Integer.parseInt(request.getParameter("projectId")); // Lấy projectId

                        String sqlSubmit = """
                            UPDATE project_registrations
                            SET submitted_file = ?, submit_time = NOW()
                            WHERE guest_id = ? AND project_id = ?
                        """;

                        try (PreparedStatement ps = conn.prepareStatement(sqlSubmit)) {
                            ps.setString(1, fileName);
                            ps.setInt(2, guestId);
                            ps.setInt(3, projectId);
                            ps.executeUpdate();       
                    }
                    break;
            }

            response.sendRedirect("ManagerProject");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Lỗi khi xử lý yêu cầu!");
        }
    }
}


