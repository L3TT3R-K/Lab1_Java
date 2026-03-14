import java.util.Scanner;

public class bai1 {
    public static double tinhChuVi() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập bán kính hình tròn: ");
        double r = sc.nextDouble();
        sc.close();
        return 2 * Math.PI * r;
    }
    public static void main(String[] args) {
        double chuVi = tinhChuVi();
        System.out.printf("Chu vi hình tròn là: %.2f", chuVi);
    }   
        
}
