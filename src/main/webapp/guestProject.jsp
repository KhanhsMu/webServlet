<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="model.project" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-primary">Chọn đề tài</h2>
    <table class="table table-success table-striped-columns">
        <thead class="table-dark">
            <tr>
                <th>Tiêu đề</th>
                <th>Mô tả</th>
                <th>Giảng viên phụ trách</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <c:forEach var="project" items="${projects}">
            <tr>
                <td>${project.title}</td>
                <td>${project.description}</td>
                <td>${project.teacherName}</td>
                <td>
                    <form action="ChooseProject" method="post" class="d-inline">
                        <input type="hidden" name="project_id" value="${project.idProject}">
                        <input type="hidden" name="action" value="register">                      
                        <button type="submit" class="btn btn-primary btn-sm">Đăng ký</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <!-- Form để Xóa đề tài -->
    <form action="ChooseProject" method="post" class="mt-3">
        <input type="hidden" name="action" value="change">
        <button type="submit" class="btn btn-danger btn-sm">Xóa đề tài</button>
    </form>
</div>



<div class="container mt-5">
    <h3 class="text-secondary">Nộp bài</h3>
    <form action="ChooseProject" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="file" class="form-label">Chọn file:</label>
            <input type="file" id="file" name="file" class="form-control" required>
        </div>
        <input type="hidden" name="action" value="submit">
        <button type="submit" class="btn btn-warning btn-sm">Nộp</button>
    </form>
</div>

<c:choose>
    <c:when test="${not empty requestScope.message and requestScope.message == 'success'}">
        <script>
            alert("✅ ${requestScope.name} thành công!");
        </script>
    </c:when>
</c:choose>
</div>
</body>
</html>