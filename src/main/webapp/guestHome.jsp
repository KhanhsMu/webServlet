<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="model.project" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chào mừng bạn </title>
</head>
<a href="LogOut.jsp">Đăng xuất</a>
<body>
<h1>Chào mừng Sinh Viên!</h1>
<h1>Danh sách đề tài</h1>
<table border="1">
    <tr>
        <th>Tiêu đề</th>
        <th>Mô tả</th>
        <th>Giảng viên phụ trách</th>
        <th>Hành động</th>
    </tr>
    <c:forEach var="project" items="${projects}">
        <tr>
            <td>${project.title}</td>
            <td>${project.description}</td>
            <td>${project.teacherName}</td>
            <td>
                <form action="ChooseProject" method="post">
                        <input type="hidden" name="project_id" value="${project.idProject}">
                        <input type="hidden" name="action" value="register">                      
                        <button type="submit">Đăng ký</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<form action="ChooseProject" method="post">
    <input type="hidden" name="action" value="change">
    <button type="submit">Xóa đề tài</button>
</form>

<h2>Nộp bài</h2>
<form action="ChooseProject" method="post" enctype="multipart/form-data">
    <input type="hidden" name="action" value="submit"> <!-- Đảm bảo có tham số action -->
    <label for="file">Chọn file:</label>
    <input type="file" id="file" name="file" required>
    <button type="submit">Nộp</button>
</form>

<c:choose>
    <c:when test="${param.message == 'success'}">
        <div style="color: green; font-weight: bold;">
            Đăng ký thành công!
        </div>
    </c:when>
    <c:when test="${param.message == 'error'}">
        <div style="color: red; font-weight: bold;">
            Có lỗi xảy ra, vui lòng thử lại.
        </div>
    </c:when>
</c:choose>
</body>
</html>