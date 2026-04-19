package Lab02.BaiTapVeNha.BTVNCau1;
import java.util.Date;

public class SinhHoat extends KhachHang {
    private double soLuong;
    private double donGia;
    private double dinhMuc;

    public SinhHoat() {}

    public SinhHoat(String maKH, String tenKH, Date ngayHoaDon,
                    double soLuong, double donGia, double dinhMuc) {
        super(maKH, tenKH, ngayHoaDon);
        this.soLuong = soLuong;
        this.donGia  = donGia;
        this.dinhMuc = dinhMuc;
    }

    @Override
    public double tinhThanhTien() {
        if (soLuong <= dinhMuc) {
            return soLuong * donGia;
        } else {
            return dinhMuc * donGia
                 + (soLuong - dinhMuc) * donGia * 2;
        }
    }

    @Override
    public void xuatThongTin() {
        System.out.printf("%-12s %-20s %10.0f %12.2f%n",
            maKH, tenKH, soLuong, tinhThanhTien());
    }
}