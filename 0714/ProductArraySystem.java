import java.util.Scanner;
public class ProductArraySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};
        boolean running = true;
        while (running) {
            printMenu();
            int choice = sc.nextInt();
            switch (choice) {
                case 1: showAllProducts(names, prices, stocks); break;
                case 2: queryProduct(sc, names, prices, stocks); break;
                case 3: buyProduct(sc, names, prices, stocks); break;
                case 4: replenishStock(sc, names, stocks); break;
                case 5: showLowStock(names, prices, stocks); break;
                case 6: showTotalValue(names, prices, stocks); break;
                case 0: running = false; break;
                default: System.out.println("無效的選項，請重新輸入！\n"); break;
            }
        }
        showSummary(names, prices, stocks);
        sc.close();
    }
    public static void printMenu() {
        System.out.println("\n=== 商品管理系統 ===");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依商品編號查詢");
        System.out.println("3. 購買商品 (扣除庫存)");
        System.out.println("4. 補充商品庫存");
        System.out.println("5. 顯示低庫存商品 (<10)");
        System.out.println("6. 顯示全部庫存總價值");
        System.out.println("0. 結束系統");
        System.out.print("請輸入選項：");
    }
    public static void showAllProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n編號\t品名\t\t價格\t庫存");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%d\t%-12s\t%d\t%d\n", (i + 1), names[i], prices[i], stocks[i]);
        }
    }
    public static void queryProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入商品編號 (1~" + names.length + ")：");
        int id = sc.nextInt() - 1;
        if (id >= 0 && id < names.length) {
            System.out.printf("品名：%s | 價格：%d | 庫存：%d\n", names[id], prices[id], stocks[id]);
        } else {
            System.out.println("錯誤：無此商品編號！");
        }
    }
    public static void buyProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入要購買的商品編號 (1~" + names.length + ")：");
        int id = sc.nextInt() - 1;
        if (id >= 0 && id < names.length) {
            System.out.print("請輸入購買數量：");
            int qty = sc.nextInt();
            if (qty > 0 && qty <= stocks[id]) {
                stocks[id] -= qty;
                System.out.println("購買成功！消費金額：" + (prices[id] * qty) + " 元，剩餘庫存：" + stocks[id]);
            } else {
                System.out.println("錯誤：數量必須大於 0 且不能超過現有庫存 (" + stocks[id] + ")！");
            }
        } else {
            System.out.println("錯誤：無此商品編號！");
        }
    }
    public static void replenishStock(Scanner sc, String[] names, int[] stocks) {
        System.out.print("請輸入要補貨的商品編號 (1~" + names.length + ")：");
        int id = sc.nextInt() - 1;
        if (id >= 0 && id < names.length) {
            System.out.print("請輸入補貨數量：");
            int qty = sc.nextInt();
            if (qty > 0) {
                stocks[id] += qty;
                System.out.println("補貨成功！" + names[id] + " 目前庫存更新為：" + stocks[id]);
            } else {
                System.out.println("錯誤：補貨數量必須大於 0！");
            }
        } else {
            System.out.println("錯誤：無此商品編號！");
        }
    }
    public static void showLowStock(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n--- 低庫存商品清單 (庫存 < 10) ---");
        boolean found = false;
        for (int i = 0; i < names.length; i++) {
            if (stocks[i] < 10) {
                System.out.printf("編號 %d: %-12s | 庫存：%d\n", (i + 1), names[i], stocks[i]);
                found = true;
            }
        }
        if (!found) System.out.println("目前沒有低庫存商品。");
    }
    public static void showTotalValue(String[] names, int[] prices, int[] stocks) {
        int totalValue = 0;
        for (int i = 0; i < names.length; i++) {
            totalValue += prices[i] * stocks[i];
        }
        System.out.println("\n目前全部商品的庫存總價值為：" + totalValue + " 元");
    }
    public static void showSummary(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n--- 系統結束操作摘要 ---");
        showAllProducts(names, prices, stocks);
    }
}