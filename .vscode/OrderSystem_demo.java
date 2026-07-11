import java.util.Scanner;

public class OrderSystem_demo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 累加器與計數器
        int totalItems = 0;
        int totalAmount = 0;
        
        // 控制主選單迴圈的變數
        boolean running = true;
        
        while (running) {
            // 顯示功能選單
            System.out.println("=== Order Menu ===");
            System.out.println("1. Black tea  $30");
            System.out.println("2. Green tea  $35");
            System.out.println("3. Coffee     $50");
            System.out.println("0. Checkout");
            System.out.print("請輸入選項：");
            
            int choice = scanner.nextInt();
            
            // 選擇 0 時結帳並準備結束
            if (choice == 0) {
                running = false;
                continue; 
            }
            
            // 使用 switch 判斷商品價格
            int price = 0;
            switch (choice) {
                case 1:
                    price = 30;
                    break;
                case 2:
                    price = 35;
                    break;
                case 3:
                    price = 50;
                    break;
                default:
                    System.out.println("無效的選項，請重新輸入！\n");
                    continue; // 重新顯示選單
            }
            
            // 輸入數量並進行驗證（確認大於 0）
            int quantity = 0;
            while (true) {
                System.out.print("請輸入數量：");
                quantity = scanner.nextInt();
                
                if (quantity > 0) {
                    break; // 數量正確，跳出驗證迴圈
                } else {
                    System.out.println("數量必須大於 0，請重新輸入！");
                }
            }
            
            // 計算單項小計並顯示
            int subtotal = price * quantity;
            System.out.println("Subtotal: " + subtotal);
            System.out.println(); // 空一行符合範例格式
            
            // 累加總數量與總金額
            totalItems += quantity;
            totalAmount += subtotal;
        }
        
        // 輸出最後結帳收據
        System.out.println("\n=== Receipt ===");
        System.out.println("Total items: " + totalItems);
        System.out.println("Total amount: " + totalAmount);
        
        scanner.close();
    }
}