<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
form {
    width: 100%;
    max-width: 500px;
    margin: 20px auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 8px;
    background-color: #f9f9f9;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    font-family: Arial, sans-serif;
}

label {
    display: block;
    margin-bottom: 8px;
    font-weight: bold;
    font-size: 14px;
    color: #333;
}

input[type="text"], textarea {
    width: 100%;
    padding: 10px;
    margin-bottom: 20px;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 14px;
    resize: none;
}

textarea {
    height: 100px;
}

button {
    padding: 10px 20px;
    background-color: #007BFF;
    color: #fff;
    border: none;
    border-radius: 4px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

button:hover {
    background-color: #0056b3;
}

button:active {
    background-color: #003d80;
}

input:focus, textarea:focus {
    border-color: #007BFF;
    outline: none;
    box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
}
.text-success {
    text-align: center;
    color: #007BFF; /* Màu xanh primary */
    font-size: 40px; /* Kích thước chữ */
    font-weight: bold; /* Đậm chữ */
    margin-bottom: 20px; /* Khoảng cách dưới */
}


@media (max-width: 768px) {
    form {
        padding: 15px;
    }

    button {
        width: 100%;
    }
}

</style>
<body>
<h2 class="text-success">Chỉnh sửa đề tài</h2>
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