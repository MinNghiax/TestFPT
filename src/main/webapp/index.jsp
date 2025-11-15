<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trang chủ - Quản lý Sinh viên</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 40px;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }
        h1 {
            color: #333;
        }
        .container {
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
            width: 500px;
        }
        ul {
            list-style: none;
            padding: 0;
        }
        li {
            margin: 15px 0;
        }
        a {
            text-decoration: none;
            font-size: 1.1em;
            padding: 12px 20px;
            background-color: #007bff;
            color: white;
            border-radius: 5px;
            display: block;
            text-align: center;
            transition: background-color 0.3s ease;
        }
        a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

    <h1>Hệ thống Quản lý Tốt nghiệp và Việc làm</h1>

    <div class="container">
        <h2>Vui lòng chọn chức năng:</h2>
        <ul>
            <li>
                <%-- Dùng ${pageContext.request.contextPath} để link luôn đúng --%>
                <a href="${pageContext.request.contextPath}/khai-bao">
                    1. Khai báo thông tin tốt nghiệp (Yêu cầu 2)
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/search-sv">
                    2. Tìm kiếm thông tin sinh viên (Yêu cầu 3.1)
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/search-cv">
                    3. Tìm kiếm thông tin việc làm (Yêu cầu 3.2)
                </a>
            </li>
        </ul>
    </div>

</body>
</html>