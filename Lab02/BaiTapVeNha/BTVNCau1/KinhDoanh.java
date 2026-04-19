package Lab02.BaiTapVeNha.BTVNCau1;
import java.util.Date;



public class KinhDoanh extends KhachHang {
    private double soLuong;
    private double donGia;

    public KinhDoanh() {}

    public KinhDoanh(String maKH, String tenKH, Date ngayHoaDon,
                     double soLuong, double donGia) {
        super(maKH, tenKH, ngayHoaDon);
        this.soLuong = soLuong;
        this.donGia  = donGia;
    }

    @Override
    public double tinhThanhTien() {
        if (soLuong <= 400) {
            return soLuong * donGia;
        } else {
            return 400 * donGia
                 + (soLuong - 400) * donGia * 1.05;
        }
    }

    @Override
    public void xuatThongTin() {
        System.out.printf("%-12s %-20s %10.0f %12.2f%n",
            maKH, tenKH, soLuong, tinhThanhTien());
    }
}