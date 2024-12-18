<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Chỉnh sửa Đề Tài</h1>
<form action="ManagerProject" method="post">
    <input type="hidden" name="action" value="edit">
    <input type="hidden" name="id" value="${id}">

    <label>Tiêu đề:</label><br>
    <input type="text" name="title" value="${title}" required><br>

    <label>Mô tả:</label><br>
    <textarea name="description" required>${description}</textarea><br>

    <label>Giảng viên phụ trách:</label><br>
    <input type="text" name="teacher" value="${teacherName}" required><br><br>

    <button type="submit">Lưu</button>
</form>
</body>
</html>