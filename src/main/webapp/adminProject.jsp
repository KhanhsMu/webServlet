<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="model.project" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý đề tài</title>
</head>
<body>
    <a href="LogOut.jsp">Đăng xuất</a>
    <hgroup>Chào mừng admin</hgroup>
    <h1>Danh sách các đề tài</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Tiêu đề</th>
                <th>Mô tả</th>
                <th>Giảng viên phụ trách</th>
                <th>Sinh Viên</th>
                <th>Thời gian nộp</th>
                <th>File nộp</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="project" items="${projects}">
                <tr>
                    <td>${project.idProject}</td>
                    <td>${project.title}</td>
                    <td>${project.description}</td>
                    <td>${project.teacherName}</td>
                    <td>${project.studentName}</td>
                    <td>${project.formattedSubmissionTime}</td>
                    <td>
                       <c:choose>
						    <c:when test="${project.submittedFile != null}">
						       <a href="${pageContext.request.contextPath}/uploads/${project.submittedFile}">
						           ${project.submittedFile}
						       </a>
						    </c:when>
						    <c:otherwise>
						        Chưa nộp
						    </c:otherwise>
						</c:choose>
                    </td>
                    <td>
                        <form action="ManagerProject" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="${project.idProject}">
                            <input type="hidden" name="action" value="delete">
                            <button type="submit">Xóa</button>
                        </form>
                        <form action="ManagerProject" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="${project.idProject}">
                            <input type="hidden" name="action" value="editForm">
                            <button type="submit">Sửa</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <h2>Thêm đề tài</h2>
    <form action="ManagerProject" method="post">
        <input type="hidden" name="action" value="add">
        <label for="title">Tiêu đề:</label>
        <input type="text" id="title" name="title" required>
        <label for="description">Mô tả:</label>
        <textarea id="description" name="description" required></textarea>
        <label for="teacher">Giảng viên phụ trách:</label>
        <input type="text" id="teacher" name="teacher" required>
        <button type="submit">Thêm</button>
    </form>
</body>
</html>
