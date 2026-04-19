package Lab02.BaiTapVeNha.BTVNCau1;
import java.text.*;
import java.util.*;



public class Main {

    static Scanner sc = new Scanner(System.in);
    static List<KhachHang> ds = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        nhapDanhSach();
        xuatHoaDon();
    }

    // ham nhap 
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
                if (kh instanceof SinhHoat)  return "1";
                if (kh instanceof KinhDoanh) return "2";
                if (kh instanceof SanXuat)   return "3";
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

    // ham xuat
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