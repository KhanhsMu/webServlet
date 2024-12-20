<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Trang đăng kí</title>
</head>
<body class="bg-light" style="height: 160vh;">

    <!-- Căn giữa Form -->
    <div class="d-flex justify-content-center align-items-center vh-100">
        <div class="card p-4 shadow" style="width: 400px;">
            <h2 class="text-center mb-4">Đăng Kí</h2>
            
            <form action="RegisterProcess" method="post"> 
            	<div class="mb-3">
                    <label for="username" class="form-label">Tên gmail</label>
                    <input type="text" class="form-control" id="email" name="email" placeholder="Nhập gmail">
                </div>
                <div class="mb-3">
                    <label for="username" class="form-label">Tài khoản</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="Nhập tên tài khoản">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Mật khẩu</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Nhập mật khẩu">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Xác nhận mật khẩu</label>
                    <input type="password" class="form-control" id="password" placeholder="Xác nhận mật khẩu">
                </div>
                <div class="d-grid">
                    <button type="submit" class="btn btn-primary mb-2">Đăng kí</button>
                    <a class="btn btn-primary link-opacity-100 mb-2" href="Login.jsp">Quay lại đăng nhập </a>
                    <a class="btn btn-primary link-opacity-100" href="ForgetPassWord.jsp">Quên mật khẩu </a>

                </div>
            </form>
        </div>
    </div>
 
</body>
</html>