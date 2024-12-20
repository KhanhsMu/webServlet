<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Header IT.UED</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <!-- CSS Tùy chỉnh -->
    <style>
        /* Định dạng chung cho thanh navbar */
        .navbar {
            background-color: #094570; /* Màu xanh dương chủ đạo */
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Đổ bóng nhẹ */
        }

        /* Thay đổi màu chữ và các thành phần */
        .navbar-brand {
            color: #fff;
            font-size: 1.5rem;
            font-weight: bold;
        }

        .nav-link {
            color: #fff !important; /* Đảm bảo màu chữ trắng */
            transition: color 0.3s;
        }

        .nav-link:hover {
            color: #ffc107 !important; /* Màu vàng khi hover */
        }

        .nav-link.active {
            font-weight: bold;
            color: #ffc107 !important; /* Làm nổi bật link đang active */
        }

        /* Button toggle (menu di động) */
        .navbar-toggler {
            border: none;
        }

        .navbar-toggler-icon {
            background-image: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' fill='%23ffffff' viewBox='0 0 30 30'%3e%3cpath stroke='rgba(255, 255, 255, 0.5)' stroke-linecap='round' stroke-miterlimit='10' stroke-width='2' d='M4 7h22M4 15h22M4 23h22'/%3e%3c/svg%3e");
        }

        /* Thanh tìm kiếm */
        .form-control {
            border-radius: 30px;
            border: 1px solid #fff;
            transition: border-color 0.3s, background-color 0.3s;
        }

        .form-control:focus {
            border-color: #ffc107;
            background-color: #f8f9fa;
        }

        .btn-outline-success {
            color: #fff;
            border-color: #ffc107;
        }

        .btn-outline-success:hover {
            background-color: #ffc107;
            border-color: #ffc107;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid">
            <a class="navbar-brand">IT.UED</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="ManagerProject">Quản lí đồ án</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="LogOut.jsp">Đăng xuất</a>
                    </li>
                </ul>
                <form class="d-flex" action="SearchTeacher" method="get" role="SearchTeacher">
				    <input class="form-control me-2" type="search" name="search" placeholder="Nhập tiêu đề cần tìm" aria-label="Search" value="${search != null ? search : ''}">
				    <button class="btn btn-outline-success" type="submit">Search</button>
				</form>
            </div>
        </div>
    </nav>
</body>
</html>