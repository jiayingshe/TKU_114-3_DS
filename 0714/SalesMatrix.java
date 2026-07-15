import java.util.Scanner;
public class SalesMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] sales = new int[3][4];
        inputSales(sc, sales);
        printMatrix(sales);
        printRowTotals(sales);
        printColTotals(sales);
        findTopProduct(sales);
        sc.close();
    }
    public static void inputSales(Scanner sc, int[][] sales) {
        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                while (true) {
                    System.out.print("請輸入商品 " + (i + 1) + " 第 " + (j + 1) + " 天銷售量：");
                    int val = sc.nextInt();
                    if (val >= 0) {
                        sales[i][j] = val;
                        break;
                    }
                    System.out.println("錯誤：銷售量不得小於 0！");
                }
            }
        }
    }
    public static void printMatrix(int[][] sales) {
        System.out.println("\n--- 銷售量矩陣報表 ---");
        System.out.println("\tDay1\tDay2\tDay3\tDay4");
        for (int i = 0; i < sales.length; i++) {
            System.out.print("Prod" + (i + 1) + "\t");
            for (int j = 0; j < sales[i].length; j++) {
                System.out.print(sales[i][j] + "\t");
            }
            System.out.println();
        }
    }
    public static void printRowTotals(int[][] sales) {
        System.out.println("\n--- 每項商品銷售總量 ---");
        for (int i = 0; i < sales.length; i++) {
            int total = 0;
            for (int j = 0; j < sales[i].length; j++) {
                total += sales[i][j];
            }
            System.out.println("商品 " + (i + 1) + " 總銷售量：" + total);
        }
    }
    public static void printColTotals(int[][] sales) {
        System.out.println("\n--- 每天全部商品銷售總量 ---");
        for (int j = 0; j < sales[0].length; j++) {
            int total = 0;
            for (int i = 0; i < sales.length; i++) {
                total += sales[i][j];
            }
            System.out.println("第 " + (j + 1) + " 天總銷售量：" + total);
        }
    }
    public static void findTopProduct(int[][] sales) {
        int maxTotal = -1;
        int topProduct = -1;
        for (int i = 0; i < sales.length; i++) {
            int total = 0;
            for (int j = 0; j < sales[i].length; j++) {
                total += sales[i][j];
            }
            if (total > maxTotal) {
                maxTotal = total;
                topProduct = i + 1;
            }
        }
        System.out.println("\n總銷售量最高的商品為：商品 " + topProduct + " (總銷售量：" + maxTotal + ")");
    }
}