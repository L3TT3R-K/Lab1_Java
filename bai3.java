import java.util.Scanner;


class PhanSo {
    private int tu;
    private int mau;

    public PhanSo(int tu, int mau) {
        this.tu = tu;
        this.mau = mau;
        if (this.mau == 0) {
            throw new IllegalArgumentException("Mẫu số không thể bằng 0");
        }
        rutGon();
    }
    public PhanSo() {
        this.tu = 0;
        this.mau = 1;
    }

    public void nhapPhanSo(Scanner sc) {
        System.out.print("Nhap tu so: ");
        this.tu = sc.nextInt();
        System.out.print("Nhap mau so: ");
        this.mau = sc.nextInt();
        if (this.mau == 0) {
            throw new IllegalArgumentException("Mẫu số không thể bằng 0");
        }
        rutGon();
    }
    public void inPhanSo() {
        if (tu == 0) {
            System.out.println("0");
        } else if (mau == 1) {
            System.out.println(tu);
        } else {
            System.out.printf("%d/%d\n", tu, mau);
        }
    }

    public PhanSo cong(PhanSo ps) {
        int tuMoi = this.tu * ps.mau + ps.tu * this.mau;
        int mauMoi = this.mau * ps.mau;
        PhanSo result = new PhanSo(tuMoi, mauMoi);
        result.rutGon();
        return new PhanSo(tuMoi, mauMoi);
    }

    public PhanSo tru(PhanSo ps) {
        int tuMoi = this.tu * ps.mau - ps.tu * this.mau;
        int mauMoi = this.mau * ps.mau;
        PhanSo result = new PhanSo(tuMoi, mauMoi);
        result.rutGon();
        return new PhanSo(tuMoi, mauMoi);
    }

    public PhanSo nhan(PhanSo ps) {
        int tuMoi = this.tu * ps.tu;
        int mauMoi = this.mau * ps.mau;
        PhanSo result = new PhanSo(tuMoi, mauMoi);
        result.rutGon();
        return new PhanSo(tuMoi, mauMoi);
    }

    public PhanSo chia(PhanSo ps) {
        if (ps.tu == 0) {
            throw new ArithmeticException("Không thể chia cho phân số bằng 0");
        }
        int tuMoi = this.tu * ps.mau;
        int mauMoi = this.mau * ps.tu;
        PhanSo result = new PhanSo(tuMoi, mauMoi);
        result.rutGon();
        return new PhanSo(tuMoi, mauMoi);
    }

    public void rutGon() {
        if (tu == 0) {
            mau = 1;
            return;
        }
        int ucln = timUCLN(Math.abs(tu), Math.abs(mau));
        tu /= ucln;
        mau /= ucln;
        if (mau < 0) {
            tu = -tu;
            mau = -mau;
        }
    }

    private int timUCLN(int a, int b) {
        if (b == 0) return a;
        return timUCLN(b, a % b);
    }



}
public class bai3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PhanSo ps1 = new PhanSo();
        System.out.println("Nhap phan so thu nhat:");
        ps1.nhapPhanSo(sc);
        PhanSo ps2 = new PhanSo();  
        System.out.println("Nhap phan so thu hai:");
        ps2.nhapPhanSo(sc);
        PhanSo tong = ps1.cong(ps2);    
        PhanSo hieu = ps1.tru(ps2);
        PhanSo tich = ps1.nhan(ps2);
        PhanSo thuong = ps1.chia(ps2);
        System.out.print("Tong hai phan so: ");
        tong.inPhanSo();    
        System.out.print("Hieu hai phan so: ");
        hieu.inPhanSo();
        System.out.print("Tich hai phan so: ");
        tich.inPhanSo();
        System.out.print("Thuong hai phan so: ");
        thuong.inPhanSo();

       

        sc.close();
    }
}


