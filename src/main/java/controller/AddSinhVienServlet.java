package controller;

import dao.SinhVienDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Nganh;
import model.Truong;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

// Đổi tên URL mapping để tránh trùng với file JSP
@WebServlet("/khai-bao")
public class AddSinhVienServlet extends HttpServlet {
    private SinhVienDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new SinhVienDAO();
    }

    // HIỂN THỊ FORM (YÊU CẦU 2.2)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy danh sách trường và ngành
        List<Truong> listTruong = dao.getDanhSachTruong();
        List<Nganh> listNganh = dao.getDanhSachNganh();

        // Gửi danh sách qua JSP
        request.setAttribute("listTruong", listTruong);
        request.setAttribute("listNganh", listNganh);

        // Chuyển hướng đến trang JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("addSinhVien.jsp");
        dispatcher.forward(request, response);
    }

    // LƯU FORM (YÊU CẦU 2.1, 2.3, 2.4)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String cmnd = request.getParameter("SoCMND");
        String hoten = request.getParameter("HoTen");
        String email = request.getParameter("Email");
        String sdt = request.getParameter("SoDT");
        String diachi = request.getParameter("DiaChi");
        String maTruong = request.getParameter("MaTruong");
        String maNganh = request.getParameter("MaNganh");
        String heTN = request.getParameter("HeTN");
        String ngayTN = request.getParameter("NgayTN");

        String error = "";

        // YÊU CẦU 2.3: Kiểm tra bắt buộc nhập
        if (cmnd == null || cmnd.trim().isEmpty()) {
            error = "Số CMND không được để trống!";
        } else if (maTruong == null || maTruong.trim().isEmpty()) {
            error = "Bạn phải chọn Trường!";
        } else if (maNganh == null || maNganh.trim().isEmpty()) {
            error = "Bạn phải chọn Ngành!";
        }

        // YÊU CẦU 2.4: Kiểm tra ngày hợp lệ
        else if (ngayTN == null || ngayTN.trim().isEmpty()) {
            error = "Ngày tốt nghiệp không được để trống!";
        } else {
            try {
                // Thử parse ngày, nếu lỗi là không hợp lệ
                LocalDate.parse(ngayTN);
            } catch (DateTimeParseException e) {
                error = "Định dạng Ngày tốt nghiệp không hợp lệ!";
            }
        }

        // Nếu có lỗi, quay lại form và báo lỗi
        if (!error.isEmpty()) {
            request.setAttribute("errorMessage", error);
            // Phải tải lại data cho ComboBox
            doGet(request, response);
            return;
        }

        // Nếu không lỗi, tiến hành lưu
        boolean result = dao.insertSinhVienAndTN(cmnd, hoten, email, sdt, diachi, maTruong, maNganh, heTN, ngayTN);

        if (result) {
            response.sendRedirect("success.jsp"); // Tạo 1 file success.jsp đơn giản
        } else {
            request.setAttribute("errorMessage", "Lưu thất bại! Có lỗi xảy ra.");
            doGet(request, response);
        }
    }
}