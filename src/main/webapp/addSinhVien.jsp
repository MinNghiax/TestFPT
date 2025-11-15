<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- BẮT BUỘC CÓ DÒNG NÀY --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Khai báo sinh viên tốt nghiệp</title>
</head>
<body>
<h2>Form khai báo tốt nghiệp</h2>

<%-- Hiển thị lỗi nếu có --%>
<c:if test="${not empty errorMessage}">
    <p style="color: red;">${errorMessage}</p>
</c:if>

<%-- SỬA ACTION TRỎ VÀO SERVLET --%>
<form action="khai-bao" method="post">

    <%-- Xóa 'required' ở đây vì ta đã validate ở server --%>
    Số CMND: <input type="text" name="SoCMND" value="${param.SoCMND}"><br><br>
    Họ tên: <input type="text" name="HoTen" value="${param.HoTen}"><br><br>
    Email: <input type="email" name="Email" value="${param.Email}"><br><br>
    Số ĐT: <input type.text" name="SoDT" value="${param.SoDT}"><br><br>
    Địa chỉ: <input type="text" name="DiaChi" value="${param.DiaChi}"><br><br>

    Trường:
    <select name="MaTruong">
        <option value="">-- Chọn trường --</option>
        <c:forEach items="${listTruong}" var="t">
            <option value="${t.maTruong}" ${param.MaTruong == t.maTruong ? 'selected' : ''}>
                ${t.tenTruong}
            </option>
        </c:forEach>
    </select><br><br>

    Ngành:
    <select name="MaNganh">
        <option value="">-- Chọn ngành --</option>
        <c:forEach items="${listNganh}" var="n">
            <option value="${n.maNganh}" ${param.MaNganh == n.maNganh ? 'selected' : ''}>
                ${n.tenNganh}
            </option>
        </c:forEach>
    </select><br><br>

    Hệ tốt nghiệp: <input type="text" name="HeTN" value="${param.HeTN}"><br><br>
    Ngày tốt nghiệp: <input type="date" name="NgayTN" value="${param.NgayTN}"><br><br>

    <button type="submit">Lưu</button>
</form>

</body>
</html>