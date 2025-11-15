package model;

public class CongViecInfo {
    private String soCMND;
    private String hoTen;
    private String maNganhTN; // Ngành tốt nghiệp
    private String maTruongTN; // Trường tốt nghiệp
    private String maNganhCV; // Ngành công ty
    private String tenCongTy;
    private String thoiGianLamViec;

    // Constructors, Getters, Setters
    public CongViecInfo() {}

    // ... Thêm Getters và Setters cho tất cả các trường trên ...
    public String getSoCMND() { return soCMND; }
    public void setSoCMND(String soCMND) { this.soCMND = soCMND; }
    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    public String getMaNganhTN() { return maNganhTN; }
    public void setMaNganhTN(String maNganhTN) { this.maNganhTN = maNganhTN; }
    public String getMaTruongTN() { return maTruongTN; }
    public void setMaTruongTN(String maTruongTN) { this.maTruongTN = maTruongTN; }
    public String getMaNganhCV() { return maNganhCV; }
    public void setMaNganhCV(String maNganhCV) { this.maNganhCV = maNganhCV; }
    public String getTenCongTy() { return tenCongTy; }
    public void setTenCongTy(String tenCongTy) { this.tenCongTy = tenCongTy; }
    public String getThoiGianLamViec() { return thoiGianLamViec; }
    public void setThoiGianLamViec(String thoiGianLamViec) { this.thoiGianLamViec = thoiGianLamViec; }
}