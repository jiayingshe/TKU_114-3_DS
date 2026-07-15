import java.util.Scanner;
public class ProductSearchSystem {
    /* 測試案例記錄 (Test Cases):
    1. 顯示全部商品: 輸入選項 1 -> 正確顯示 5 筆商品資料。
    2. 完整名稱搜尋 (前後空白與大小寫): 輸入選項 2 -> 輸入 "  mOuSe  " -> 找到第 2 項商品 Mouse。
    3. 完整名稱搜尋 (找不到): 輸入選項 2 -> 輸入 "iPad" -> 顯示找不到商品。
    4. 部分名稱搜尋 (多筆結果): 輸入選項 3 -> 輸入 "set" -> 找到 Headset；輸入 "o" -> 找到 Keyboard, Mouse, Monitor。
    5. 最長名稱商品: 輸入選項 4 -> 顯示 "Keyboard" 與 "USB Cable" (兩者皆為 8 個字元，顯示其一或全部)。
    6. 關鍵字位置搜尋: 輸入選項 5 -> 輸入 "o" -> 顯示 Keyboard 索引為 4, Mouse 索引為 1, Monitor 索引為 1 等結果。
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};
        boolean running = true;
        while (running) {
            printMenu();
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1: showAll(names, prices, stocks); break;
                case 2: exactSearch(sc, names, prices, stocks); break;
                case 3: partialSearch(sc, names, prices, stocks); break;
                case 4: showLongestName(names); break;
                case 5: showKeywordIndex(sc, names); break;
                case 0: running = false; break;
                default: System.out.println("無效的選項，請重新輸入！\n"); break;
            }
        }
        sc.close();
    }
    public static void printMenu() {
        System.out.println("\n=== 商品名稱搜尋系統 ===");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 完整名稱搜尋 (忽略大小寫與前後空白)");
        System.out.println("3. 部分名稱搜尋 (顯示多筆)");
        System.out.println("4. 顯示名稱最長的商品");
        System.out.println("5. 顯示關鍵字在商品名稱第一次出現的位置");
        System.out.println("0. 結束");
        System.out.print("請輸入選項：");
    }
    public static void showAll(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n品名\t\t價格\t庫存");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%-12s\t%d\t%d\n", names[i], prices[i], stocks[i]);
        }
    }
    public static void exactSearch(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入完整商品名稱：");
        String query = sc.nextLine().trim();
        boolean found = false;
        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(query)) {
                System.out.printf("找到商品！品名：%s | 價格：%d | 庫存：%d\n", names[i], prices[i], stocks[i]);
                found = true;
                break;
            }
        }
        if (!found) System.out.println("找不到符合完整名稱的商品。");
    }
    public static void partialSearch(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入搜尋關鍵字：");
        String query = sc.nextLine().toLowerCase();
        boolean found = false;
        System.out.println("\n--- 搜尋結果 ---");
        for (int i = 0; i < names.length; i++) {
            if (names[i].toLowerCase().contains(query)) {
                System.out.printf("品名：%s | 價格：%d | 庫存：%d\n", names[i], prices[i], stocks[i]);
                found = true;
            }
        }
        if (!found) System.out.println("找不到任何包含該關鍵字的商品。");
    }
    public static void showLongestName(String[] names) {
        String longest = names[0];
        for (String name : names) {
            if (name.length() > longest.length()) {
                longest = name;
            }
        }
        System.out.println("\n名稱最長的商品為：" + longest + " (字數：" + longest.length() + ")");
    }
    public static void showKeywordIndex(Scanner sc, String[] names) {
        System.out.print("請輸入欲定位的關鍵字：");
        String query = sc.nextLine().toLowerCase();
        System.out.println("\n--- 關鍵字第一次出現位置 ---");
        for (String name : names) {
            int idx = name.toLowerCase().indexOf(query);
            if (idx != -1) {
                System.out.println("商品：[" + name + "] -> 關鍵字首次出現於索引：" + idx);
            } else {
                System.out.println("商品：[" + name + "] -> 未包含該關鍵字");
            }
        }
    }
}