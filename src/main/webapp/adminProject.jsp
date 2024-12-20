<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="model.project" %>
<%@ include file="headerAdmin.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý đề tài</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body >

    <div class="container my-5">
        
        <h3 class="mb-4">Danh sách các đề tài</h3>
        <div class="table-responsive">
            <table class="table table-success table-striped-columns">
                <thead class="table-dark">
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
                                        <a href="${pageContext.request.contextPath}/uploads/${project.submittedFile}" class="btn btn-link">
                                            ${project.submittedFile}
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="text-muted">Chưa nộp</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <div class="d-flex gap-2">
                                    <form action="ManagerProject" method="post">
                                        <input type="hidden" name="id" value="${project.idProject}">
                                        <input type="hidden" name="action" value="delete">
                                        <button type="submit" class="btn btn-danger btn-sm">Xóa</button>
                                    </form>
                                    <form action="ManagerProject" method="post">
                                        <input type="hidden" name="id" value="${project.idProject}">
                                        <input type="hidden" name="action" value="editForm">
                                        <button type="submit" class="btn btn-warning btn-sm">Sửa</button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <h3 class="mt-5 text-info">Thêm đề tài</h3>
        <form action="ManagerProject" method="post" class="p-4 bg-white shadow rounded">
            <input type="hidden" name="action" value="add">
            <div class="mb-3">
                <label for="title" class="form-label">Tiêu đề:</label>
                <input type="text" class="form-control" id="title" name="title" required>
            </div>
            <div class="mb-3">
                <label for="description" class="form-label">Mô tả:</label>
                <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
            </div>
            <div class="mb-3">
                <label for="teacher" class="form-label">Giảng viên phụ trách:</label>
                <input type="text" class="form-control" id="teacher" name="teacher" required>
            </div>
            <button type="submit" class="btn btn-success w-100">Thêm</button>
        </form>

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
