import java.text.*;
import java.util.*;

abstract class KhachHang {
    protected String maKH;
    protected String tenKH;
    protected Date ngayHoaDon;

    public KhachHang() {}

    public KhachHang(String maKH, String tenKH, Date ngayHoaDon) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.ngayHoaDon = ngayHoaDon;
    }

    public String getMaKH()      { return maKH; }
    public String getTenKH()     { return tenKH; }
    public Date getNgayHoaDon()  { return ngayHoaDon; }

    public abstract double tinhThanhTien();
    public abstract void xuatThongTin();
}

class SinhHoat extends KhachHang {
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

class KinhDoanh extends KhachHang {
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

class SanXuat extends KhachHang {
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
        if (loaiDien.trim().equalsIgnoreCase("2 pha")) {
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

public class Main {

    static Scanner sc = new Scanner(System.in);
    static List<KhachHang> ds = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        nhapDanhSach();
        xuatHoaDon();
    }

    static void nhapDanhSach() throws Exception {
        while (true) {
            System.out.print("Mã KH (Enter để kết thúc): ");
            String ma = sc.nextLine().trim();
            if (ma.isEmpty()) break;

            System.out.print("Tên KH: ");
            String ten = sc.nextLine().trim();

            String loaiYeuCau = timLoaiTheoMaTen(ma, ten);

            System.out.print("Ngày hóa đơn (dd/MM/yyyy): ");
            Date ngay = new SimpleDateFormat("dd/MM/yyyy")
                            .parse(sc.nextLine().trim());

            String loai = (loaiYeuCau != null) ? loaiYeuCau : chonLoai();

            KhachHang kh = nhapTheoLoai(ma, ten, ngay, loai);
            if (kh != null) ds.add(kh);
        }
    }

    static String timLoaiTheoMaTen(String ma, String ten) {
        for (KhachHang kh : ds) {
            if (kh.getMaKH().equals(ma) && kh.getTenKH().equals(ten)) {
                String loai = "";
                if (kh instanceof SinhHoat)  loai = "1";
                if (kh instanceof KinhDoanh) loai = "2";
                if (kh instanceof SanXuat)   loai = "3";
                System.out.println(">> Mã+Tên đã tồn tại, tự động dùng loại: " + loai);
                return loai;
            }
        }
        return null;
    }

    static String chonLoai() {
        System.out.println("Loại: 1-Sinh hoạt  2-Kinh doanh  3-Sản xuất");
        return sc.nextLine().trim();
    }

    static KhachHang nhapTheoLoai(String ma, String ten,
                                   Date ngay, String loai) {
        System.out.print("Số lượng điện: ");
        double sl = Double.parseDouble(sc.nextLine());
        System.out.print("Đơn giá: ");
        double dg = Double.parseDouble(sc.nextLine());

        switch (loai) {
            case "1":
                System.out.print("Định mức: ");
                double dm = Double.parseDouble(sc.nextLine());
                return new SinhHoat(ma, ten, ngay, sl, dg, dm);
            case "2":
                return new KinhDoanh(ma, ten, ngay, sl, dg);
            case "3":
                System.out.print("Loại điện (2 pha / 3 pha): ");
                String ld = sc.nextLine().trim();
                return new SanXuat(ma, ten, ngay, sl, dg, ld);
            default:
                System.out.println("Loại không hợp lệ.");
                return null;
        }
    }

    static void xuatHoaDon() {
        System.out.print("Nhập tháng cần xuất (MM): ");
        int thang = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Nhập năm cần xuất (yyyy): ");
        int nam = Integer.parseInt(sc.nextLine().trim());

        System.out.println("\n===== HÓA ĐƠN THÁNG " + thang + "/" + nam + " =====");
        System.out.printf("%-12s %-20s %10s %12s%n",
            "Mã KH", "Tên KH", "Số lượng", "Thành tiền");
        System.out.println("-".repeat(58));

        Calendar cal = Calendar.getInstance();
        boolean found = false;
        for (KhachHang kh : ds) {
            cal.setTime(kh.getNgayHoaDon());
            if (cal.get(Calendar.MONTH) + 1 == thang
             && cal.get(Calendar.YEAR)  == nam) {
                kh.xuatThongTin();
                found = true;
            }
        }
        if (!found)
            System.out.println("Không có hóa đơn nào trong tháng này.");
    }
}