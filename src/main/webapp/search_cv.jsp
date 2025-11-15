<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Tìm kiếm việc làm</title>
</head>
<body>
    <h2>Form 2: Tìm kiếm thông tin tốt nghiệp và việc làm</h2>
    <form action="search-cv" method="get">
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
                <th>Mã Ngành (TN)</th>
                <th>Mã Trường (TN)</th>
                <th>Mã Ngành (CV)</th>
                <th>Tên Công Ty</th>
                <th>Thời Gian Làm</th>
            </tr>
            <c:forEach items="${resultList}" var="cv">
                <tr>
                    <td>${cv.soCMND}</td>
                    <td>${cv.hoTen}</td>
                    <td>${cv.maNganhTN}</td>
                    <td>${cv.maTruongTN}</td>
                    <td>${cv.maNganhCV}</td>
                    <td>${cv.tenCongTy}</td>
                    <td>${cv.thoiGianLamViec}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>