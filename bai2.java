import java.util.Scanner;

public class bai2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.err.println("Nhap 2 so a va b:");
        int a = sc.nextInt(), b = sc.nextInt();
        double chia = (double)a/b;
        System.out.printf("Ket qua a/b la: %.3f", chia);
        sc.close();
    }

}
