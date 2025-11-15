<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Tìm kiếm sinh viên</title>
</head>
<body>
    <h2>Form 1: Tìm kiếm thông tin cơ bản của sinh viên</h2>
    <form action="search-sv" method="get">
        Nhập tên hoặc CMND:
        <input type="text" name="keyword" value="${keyword}">
        <button type="submit">Tìm</button>
    </form>

    <c:if test="${not empty resultList}">
        <h3>Kết quả tìm kiếm:</h3>
        <table border="1">
            <tr>
                <th>Số CMND</th>
                <th>Họ Tên</th>
                <th>Email</th>
                <th>Số ĐT</th>
                <th>Địa Chỉ</th>
            </tr>
            <c:forEach items="${resultList}" var="sv">
                <tr>
                    <td>${sv.soCMND}</td>
                    <td>${sv.hoTen}</td>
                    <td>${sv.email}</td>
                    <td>${sv.soDT}</td>
                    <td>${sv.diaChi}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>