import java.util.Scanner;
public class ProductManagementSystem {
    /* 測試案例記錄 (Test Cases):
    1. 顯示全部商品: 選項 1 -> 輸出初始的 5 筆商品表格。
    2. 完整名稱搜尋 (忽略大小寫與前後空白): 選項 2 -> 輸入 "  mOuSe  " -> 成功找到 Mouse。
    3. 新增商品 (正常狀況): 選項 3 -> 輸入 "Speaker", 1500, 10 -> 成功新增。
    4. 新增商品 (重複品名): 選項 3 -> 輸入 "Keyboard" -> 顯示重複品名錯誤，拒絕新增。
    5. 新增商品 (不合法資料): 選項 3 -> 輸入 "iPad", -500, 5 -> 觸發 Product 類別建構子驗證並印出錯誤，程式不中斷。
    6. 出售商品 (成功): 選項 4 -> 選擇 "Mouse", 數量 5 -> 扣除庫存成功，剩餘 15。
    7. 出售商品 (庫存不足): 選項 4 -> 選擇 "Monitor", 數量 10 -> 顯示庫存不足 (現有5)。
    8. 補充庫存: 選項 5 -> 選擇 "Monitor", 補貨 15 -> 庫存更新為 20。
    9. 修改價格: 選項 6 -> 選擇 "Keyboard", 新價格 950 -> 價格成功更新。
    10. 低庫存與庫存總價值: 選項 7 與 8 -> 正常篩選庫存 < 10 的商品並精確計算總價值。
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Product[] products = new Product[10];
        int count = initProducts(products);
        boolean running = true;
        while (running) {
            printMenu();
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1: showAll(products, count); break;
                case 2: exactSearch(sc, products, count); break;
                case 3: count = addProduct(sc, products, count); break;
                case 4: sellProduct(sc, products, count); break;
                case 5: restockProduct(sc, products, count); break;
                case 6: updatePrice(sc, products, count); break;
                case 7: showLowStock(products, count); break;
                case 8: showTotalValue(products, count); break;
                case 0: running = false; break;
                default: System.out.println("無效的選項，請重新輸入！\n"); break;
            }
        }
        showSummary(products, count);
        sc.close();
    }
    public static int initProducts(Product[] products) {
        products[0] = new Product("Keyboard", 890, 12);
        products[1] = new Product("Mouse", 490, 20);
        products[2] = new Product("Monitor", 5200, 5);
        products[3] = new Product("USB Cable", 250, 30);
        products[4] = new Product("Headset", 1290, 8);
        return 5;
    }
    public static void printMenu() {
        System.out.println("\n=== 物件導向商品管理系統 ===");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依完整名稱搜尋");
        System.out.println("3. 新增商品");
        System.out.println("4. 出售商品");
        System.out.println("5. 補充庫存");
        System.out.println("6. 修改商品價格");
        System.out.println("7. 顯示低庫存商品");
        System.out.println("8. 顯示全部庫存總價值");
        System.out.println("0. 結束並顯示操作摘要");
        System.out.print("請輸入選項：");
    }
    public static void showAll(Product[] products, int count) {
        System.out.println("\n品名\t\t價格\t庫存");
        for (int i = 0; i < count; i++) {
            System.out.println(products[i]);
        }
    }
    public static int findProductIndex(Product[] products, int count, String name) {
        for (int i = 0; i < count; i++) {
            if (products[i].getName().equalsIgnoreCase(name.trim())) {
                return i;
            }
        }
        return -1;
    }
    public static void exactSearch(Scanner sc, Product[] products, int count) {
        System.out.print("請輸入完整商品名稱：");
        String name = sc.nextLine();
        int idx = findProductIndex(products, count, name);
        if (idx != -1) {
            System.out.println("\n找到商品！品名\t\t價格\t庫存");
            System.out.println(products[idx]);
        } else {
            System.out.println("找不到符合完整名稱的商品。");
        }
    }
    public static int addProduct(Scanner sc, Product[] products, int count) {
        if (count >= products.length) {
            System.out.println("錯誤：商品庫存空間已滿，無法新增！");
            return count;
        }
        System.out.print("請輸入新商品名稱：");
        String name = sc.nextLine();
        if (findProductIndex(products, count, name) != -1) {
            System.out.println("錯誤：不可新增重複名稱的商品！");
            return count;
        }
        System.out.print("請輸入價格：");
        int price = sc.nextInt();
        System.out.print("請輸入初始庫存：");
        int stock = sc.nextInt();
        try {
            products[count] = new Product(name, price, stock);
            System.out.println("商品新增成功！");
            return count + 1;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + " 新增失敗。");
        }
        return count;
    }
    public static void sellProduct(Scanner sc, Product[] products, int count) {
        System.out.print("請輸入欲購買的商品名稱：");
        String name = sc.nextLine();
        int idx = findProductIndex(products, count, name);
        if (idx == -1) {
            System.out.println("錯誤：無此商品！");
            return;
        }
        System.out.print("請輸入欲購買數量：");
        int qty = sc.nextInt();
        if (products[idx].sell(qty)) {
            System.out.println("出售成功！總金額：" + (products[idx].getPrice() * qty) + " 元");
        }
    }
    public static void restockProduct(Scanner sc, Product[] products, int count) {
        System.out.print("請輸入欲補貨的商品名稱：");
        String name = sc.nextLine();
        int idx = findProductIndex(products, count, name);
        if (idx == -1) {
            System.out.println("錯誤：無此商品！");
            return;
        }
        System.out.print("請輸入補貨數量：");
        int qty = sc.nextInt();
        products[idx].restock(qty);
        System.out.println("補貨成功！目前庫存：" + products[idx].getStock());
    }
    public static void updatePrice(Scanner sc, Product[] products, int count) {
        System.out.print("請輸入欲修改價格的商品名稱：");
        String name = sc.nextLine();
        int idx = findProductIndex(products, count, name);
        if (idx == -1) {
            System.out.println("錯誤：無此商品！");
            return;
        }
        System.out.print("請輸入新價格：");
        int newPrice = sc.nextInt();
        products[idx].setPrice(newPrice);
        System.out.println("價格修改程序完成。");
    }
    public static void showLowStock(Product[] products, int count) {
        System.out.println("\n--- 低庫存商品清單 (<10) ---");
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (products[i].isLowStock()) {
                System.out.println(products[i]);
                found = true;
            }
        }
        if (!found) System.out.println("目前沒有低庫存商品。");
    }
    public static void showTotalValue(Product[] products, int count) {
        int totalValue = 0;
        for (int i = 0; i < count; i++) {
            totalValue += products[i].getInventoryValue();
        }
        System.out.println("\n目前全部商品的庫存總價值為：" + totalValue + " 元");
    }
    public static void showSummary(Product[] products, int count) {
        System.out.println("\n--- 系統結束操作摘要 ---");
        showAll(products, count);
    }
}