package dao;

import model.CongViecInfo;
import model.Nganh;
import model.SinhVien;
import model.Truong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SinhVienDAO {

    private Connection getConnect() throws Exception {
        // Giữ nguyên thông tin kết nối của bạn
        String url = "jdbc:mysql://localhost:3307/qlsv";
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        return DriverManager.getConnection(url, "root", "040904");
    }

    // HÀM CŨ CỦA BẠN (Đã sửa 1 chút để trả về boolean cho dễ)
    public boolean insertSinhVienAndTN(String cmnd, String hoten, String email, String sdt,
                                       String diachi, String maTruong, String maNganh,
                                       String heTN, String ngayTN) {

        String sql1 = "INSERT INTO SINHVIEN(SoCMND, HoTen, Email, SoDT, DiaChi) VALUES (?, ?, ?, ?, ?)";
        String sql2 = "INSERT INTO TOT_NGHIEP(SoCMND, MaTruong, MaNganh, HeTN, NgayTN, LoaiTN) VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        try {
            conn = getConnect();
            PreparedStatement st1 = conn.prepareStatement(sql1);
            PreparedStatement st2 = conn.prepareStatement(sql2);

            conn.setAutoCommit(false); // Bắt đầu transaction

            st1.setString(1, cmnd);
            st1.setString(2, hoten);
            st1.setString(3, email);
            st1.setString(4, sdt);
            st1.setString(5, diachi);
            int rs1 = st1.executeUpdate();

            st2.setString(1, cmnd);
            st2.setString(2, maTruong);
            st2.setString(3, maNganh);
            st2.setString(4, heTN);
            st2.setString(5, ngayTN);
            st2.setString(6, "Đúng hạn"); // Giả sử mặc định
            int rs2 = st2.executeUpdate();

            conn.commit(); // Hoàn tất transaction
            st1.close();
            st2.close();
            conn.close();
            return (rs1 > 0 && rs2 > 0);

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback(); // Rollback nếu có lỗi
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }

    // ======= HÀM MỚI CHO YÊU CẦU 2.2 =======
    public List<Truong> getDanhSachTruong() {
        List<Truong> list = new ArrayList<>();
        String sql = "SELECT MaTruong, TenTruong FROM TRUONG";
        try (Connection conn = getConnect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Truong(rs.getString("MaTruong"), rs.getString("TenTruong")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Nganh> getDanhSachNganh() {
        List<Nganh> list = new ArrayList<>();
        String sql = "SELECT MaNganh, TenNganh FROM NGANH";
        try (Connection conn = getConnect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Nganh(rs.getString("MaNganh"), rs.getString("TenNganh")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ... (Thêm vào cuối file SinhVienDAO.java) ...

    // HÀM MỚI CHO YÊU CẦU 3.1
    public List<SinhVien> searchSinhVien(String keyword) {
        List<SinhVien> list = new ArrayList<>();
        // Tìm kiếm theo tên hoặc CMND
        String sql = "SELECT * FROM SINHVIEN WHERE HoTen LIKE ? OR SoCMND LIKE ?";
        try (Connection conn = getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new SinhVien(
                            rs.getString("SoCMND"),
                            rs.getString("HoTen"),
                            rs.getString("Email"),
                            rs.getString("SoDT"),
                            rs.getString("DiaChi")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // HÀM MỚI CHO YÊU CẦU 3.2
    public List<CongViecInfo> searchCongViec(String keyword) {
        List<CongViecInfo> list = new ArrayList<>();
        // Đây là câu query phức tạp join nhiều bảng
        String sql = "SELECT sv.SoCMND, sv.HoTen, tn.MaNganh, tn.MaTruong, cv.MaNganh, cv.TenCongTy, cv.ThoiGianLamViec " +
                "FROM SINHVIEN sv " +
                "LEFT JOIN TOT_NGHIEP tn ON sv.SoCMND = tn.SoCMND " +
                "LEFT JOIN CONG_VIEC cv ON sv.SoCMND = cv.SoCMND " +
                "WHERE sv.HoTen LIKE ? OR sv.SoCMND LIKE ?";

        try (Connection conn = getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CongViecInfo info = new CongViecInfo();
                    info.setSoCMND(rs.getString("SoCMND"));
                    info.setHoTen(rs.getString("HoTen"));
                    info.setMaNganhTN(rs.getString("MaNganh")); // Của Tốt Nghiệp
                    info.setMaTruongTN(rs.getString("MaTruong"));
                    info.setMaNganhCV(rs.getString(5)); // Của Công Việc (lấy theo index)
                    info.setTenCongTy(rs.getString("TenCongTy"));
                    info.setThoiGianLamViec(rs.getString("ThoiGianLamViec"));
                    list.add(info);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}