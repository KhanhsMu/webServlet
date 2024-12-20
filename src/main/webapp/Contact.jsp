<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin liên hệ</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
 <style>
  body {
            font-family: Arial, sans-serif;
        }
        /* Section Background */
        .contact-section {
            background-color: #f5f5f5; /* Màu xám nhạt */
            padding: 50px 20px; /* Khoảng cách trên và dưới */
            text-align: center; /* Căn giữa nội dung */
        }

        /* Contact Header */
        .contact-section h2 {
            font-size: 2.5rem;
            color: #556B2F; /* Màu xanh lá cây đậm */
            margin-bottom: 15px;
        }

        /* Subtext */
        .contact-section p {
            font-size: 1.2rem;
            color: #555; /* Màu chữ xám đậm */
        }
        
        .container {
			    display: flex;
			    justify-content: space-between; /* Đặt nội dung hai bên trái và phải */
			    align-items: flex-start; /* Canh nội dung ở đầu */
			    padding: 20px;
			}
			
			/* Styling cho phần bên trái */
			.left {
			    flex: 0 0 25%; /* Chiếm 25% chiều rộng */
			}
			
			.left h3 {
			    font-size: 1.2rem;
			    margin: 10px 0;
			}
			
			.left a {
			    color: #007BFF;
			    text-decoration: none;
			}
			
			.left a:hover {
			    text-decoration: underline;
			}
			
			/* Styling cho phần bên phải */
			.right {
			    flex: 0 0 65%; /* Chiếm 65% chiều rộng */
			    text-align: right;
			}
			
			.right iframe {
			    width: 100%; /* Đảm bảo iframe không vượt quá giới hạn */
			    height: 500px;
			    border: none;
			    margin-top: 10px;
			}
			
			/* Để biểu tượng căn chỉnh đẹp */
			i {
			    margin-right: 10px;
			    color: #007BFF;
			}

    </style>
</head>
<body>
 <section class="contact-section">
        <h2>Liên hệ</h2>
        <p>Chúng tôi luôn sẵn sàng kết nối với bạn nếu bạn cần hãy tìm địa chỉ các mạng xã hội của chúng tôi và các thông tin chi tiết khác để liên hệ với chúng tôi qua email.</p>
    </section>
    
     <div class="container">
        <div class="left">
            <h3><i class="fas fa-home"></i><a href="https://ued.udn.vn/">Web Page</a></h3>
            <h3><i class="fas fa-envelope"></i>Email: <a href="mailto:ued@gmail.com">ued@gmail.com</a></h3>
            <h3><i class="fas fa-phone"></i>Phone: 090-512-1281</h3>
            <i class="fab fa-facebook"></i>
            <a href="https://www.facebook.com/groups/lienchidoantinhoc.spdn" target="_blank">Liên Chi Tin Học</a> <br>
            <i class="fab fa-instagram"></i>
            <a href="https://www.instagram.com/" target="_blank">lien_chi_tin_hoc</a> 
        </div>

        <div class="right">
            
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3834.0740370786148!2d108.15654371071442!3d16.06164738455254!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31421924682e8689%3A0x48eb0bdbeec05215!2zVHLGsOG7nW5nIMSQ4bqhaSBI4buNYyBTxrAgUGjhuqFtIC0gxJDhuqFpIGjhu41jIMSQw6AgTuG6tW5n!5e0!3m2!1svi!2s!4v1734703978523!5m2!1svi!2s"
                allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>
    </div>

</body>
</html>