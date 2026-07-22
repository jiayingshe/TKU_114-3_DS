import java.util.Scanner;

public class ShoppingCartSystem {
    private static CartItem[] cart = new CartItem[100];
    private static int itemCount = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("請選擇操作項目 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addItem();
                    break;
                case "2":
                    updateQuantity();
                    break;
                case "3":
                    removeItem();
                    break;
                case "4":
                    listCart();
                    break;
                case "5":
                    showGrandTotal();
                    break;
                case "6":
                    running = false;
                    System.out.println("感謝使用購物車系統，祝您購物愉快！");
                    break;
                default:
                    System.out.println("輸入無效，請選擇 1 到 6 之中的選項。");
            }
            System.out.println();
        }
    }

    public static void printMenu() {
        System.out.println("=== 購物車管理系統 ===");
        System.out.println("1. 加入商品 / 增加數量");
        System.out.println("2. 修改商品數量");
        System.out.println("3. 移除商品");
        System.out.println("4. 檢視購物車清單");
        System.out.println("5. 計算購物車總額");
        System.out.println("6. 結帳離開");
        System.out.println("======================");
    }

    public static void addItem() {
        System.out.print("請輸入商品代碼: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("失敗：商品代碼不可為空白！");
            return;
        }

        System.out.print("請輸入數量: ");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine().trim());
            if (quantity <= 0) {
                System.out.println("失敗：數量必須大於 0！");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("失敗：數量必須為有效整數！");
            return;
        }

        int existingIndex = findIndexById(id);
        if (existingIndex != -1) {
            cart[existingIndex].addQuantity(quantity);
            System.out.println("成功：商品已存在購物車中，已累加數量！目前總數量為 " + cart[existingIndex].getQuantity());
            return;
        }

        if (itemCount >= cart.length) {
            System.out.println("失敗：購物車已滿！");
            return;
        }

        System.out.print("請輸入商品名稱: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("失敗：商品名稱不可為空白！");
            return;
        }

        System.out.print("請輸入商品單價: ");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine().trim());
            if (price < 0) {
                System.out.println("失敗：單價不可為負數！");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("失敗：單價必須為有效數字！");
            return;
        }

        cart[itemCount] = new CartItem(id, name, price, quantity);
        itemCount++;
        System.out.println("成功：已將 [" + name + "] 加入購物車！");
    }

    public static void updateQuantity() {
        System.out.print("請輸入要修改數量的商品代碼: ");
        String id = scanner.nextLine().trim();
        int index = findIndexById(id);

        if (index == -1) {
            System.out.println("失敗：購物車內找不到代碼為 [" + id + "] 的商品。");
            return;
        }

        System.out.print("請輸入新的數量: ");
        int newQuantity;
        try {
            newQuantity = Integer.parseInt(scanner.nextLine().trim());
            if (newQuantity <= 0) {
                System.out.println("失敗：更新數量必須大於 0（小於或等於 0 不接受更新）！");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("失敗：數量必須為有效整數！");
            return;
        }

        cart[index].setQuantity(newQuantity);
        System.out.println("成功：已將 [" + cart[index].getName() + "] 的數量更新為 " + newQuantity);
    }

    public static void removeItem() {
        System.out.print("請輸入要移除的商品代碼: ");
        String id = scanner.nextLine().trim();
        int index = findIndexById(id);

        if (index == -1) {
            System.out.println("失敗：購物車內找不到代碼為 [" + id + "] 的商品。");
            return;
        }

        String targetName = cart[index].getName();
        for (int i = index; i < itemCount - 1; i++) {
            cart[i] = cart[i + 1];
        }
        cart[itemCount - 1] = null;
        itemCount--;

        System.out.println("成功：已從購物車中移除 [" + targetName + "]");
    }

    public static void listCart() {
        if (itemCount == 0) {
            System.out.println("購物車目前是空的。");
            return;
        }
        System.out.println("--- 購物車清單 (共 " + itemCount + " 項商品) ---");
        for (int i = 0; i < itemCount; i++) {
            System.out.println((i + 1) + ". " + cart[i]);
        }
    }

    public static void showGrandTotal() {
        double grandTotal = 0.0;
        for (int i = 0; i < itemCount; i++) {
            grandTotal += cart[i].getTotalPrice();
        }
        System.out.println("購物車總金額為：$" + grandTotal);
    }

    private static int findIndexById(String id) {
        if (id == null || id.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < itemCount; i++) {
            if (cart[i].getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }
}