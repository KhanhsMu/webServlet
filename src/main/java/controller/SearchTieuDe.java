package controller;

import java.io.IOException;
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

import model.DBConnect;
import model.project;

/**
 * Servlet implementation class SearchTieuDe
 */
@WebServlet("/SearchTieuDe")
public class SearchTieuDe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchTieuDe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String search = request.getParameter("search"); // Get the search term
	    
	    try (Connection conn = DBConnect.getConnection()) {
	        // Base SQL query
	        String sql = """
	            SELECT 
	                p.idProject, 
	                p.title, 
	                p.description, 
	                admin.username AS teacherName
	            FROM projects p
	            LEFT JOIN users admin ON p.admin_id = admin.idUser
	        """;

	        // If there is a search term, filter by title
	        if (search != null && !search.trim().isEmpty()) {
	            sql += " WHERE p.title LIKE ?"; // Add WHERE clause for title search
	        }

	        try (PreparedStatement ps = conn.prepareStatement(sql)) {
	            // If searching, set the search parameter
	            if (search != null && !search.trim().isEmpty()) {
	                ps.setString(1, "%" + search + "%"); // Use wildcard to search for matching titles
	            }
	            
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

	            // Set the projects list as a request attribute
	            request.setAttribute("projects", projects);
	            request.setAttribute("search", search); // Pass the search term to the view
	            request.getRequestDispatcher("guestProject.jsp").forward(request, response);
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
	        response.getWriter().write("Lỗi khi tải danh sách đề tài: " + e.getMessage());
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
	    