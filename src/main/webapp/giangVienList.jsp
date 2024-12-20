<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
	<h1>Danh Sách Giảng Viên</h1>
    <table class="table table-success table-striped-columns">
    <thead class="table-dark">
            <tr>
                <th>Họ Tên</th>
                <th>Quê Quán</th>
                <th>Ngày Sinh</th>
                <th>Chuyên Ngành</th>
                <th>Số Điện Thoại</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Map<String, String>> giangVienList = (List<Map<String, String>>) request.getAttribute("giangVienList");
                for (Map<String, String> giangVien : giangVienList) {
            %>
                <tr>
                    <td><%= giangVien.get("HoTen") %></td>
                    <td><%= giangVien.get("QueQuan") %></td>
                    <td><%= giangVien.get("NgaySinh") %></td>
                    <td><%= giangVien.get("ChuyenNganh") %></td>
                    <td><%= giangVien.get("SDT") %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>