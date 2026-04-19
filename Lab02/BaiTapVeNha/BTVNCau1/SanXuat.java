package Lab02.BaiTapVeNha.BTVNCau1;
import java.util.Date;



public class SanXuat extends KhachHang {
    private double soLuong;
    private double donGia;
    private String loaiDien; 

    public SanXuat() {}

    public SanXuat(String maKH, String tenKH, Date ngayHoaDon,
                   double soLuong, double donGia, String loaiDien) {
        super(maKH, tenKH, ngayHoaDon);
        this.soLuong  = soLuong;
        this.donGia   = donGia;
        this.loaiDien = loaiDien;
    }

    @Override
    public double tinhThanhTien() {
        double base = soLuong * donGia;
        if (loaiDien.equals("2 pha")) {
            return (soLuong > 200) ? base * 0.98 : base;
        } else { 
            return (soLuong > 150) ? base * 0.97 : base;
        }
    }

    @Override
    public void xuatThongTin() {
        System.out.printf("%-12s %-20s %10.0f %12.2f%n",
            maKH, tenKH, soLuong, tinhThanhTien());
    }
}