package Lab02.BaiTapVeNha.BTVNCau1;
import java.util.Date;

public abstract class KhachHang {

    protected String maKH;
    protected String tenKH;
    protected Date ngayHoaDon;

    public KhachHang() {
    }

    public KhachHang(String maKH, String tenKH, Date ngayHoaDon) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.ngayHoaDon = ngayHoaDon;
    }

    public String getMaKH() {
        return maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public Date getNgayHoaDon() {
        return ngayHoaDon;
    }

    public abstract double tinhThanhTien();
    
    public abstract void xuatThongTin();
}
