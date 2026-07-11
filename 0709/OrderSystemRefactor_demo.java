import java.util.Scanner;
public class OrderSystemRefactor_demo {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int totalItems = 0;
        int totalAmount = 0;
        boolean running = true;

        while (running) {
        
            displayMenu();
            int choice = scanner.nextInt();
            if (choice == 0) {
                running = false;
                continue; 
            }
            int price = getProductPrice(choice);
            if (price == -1) {
                System.out.println("無效的選項，請重新輸入！\n");
                continue;
            }
            int quantity = getValidQuantity();
            int subtotal = calculateSubtotal(price, quantity);
            System.out.println("Subtotal: " + subtotal);
            System.out.println();
            totalItems += quantity;
            totalAmount += subtotal;
        }
        printReceipt(totalItems, totalAmount);
        
        scanner.close();
    }
    public static void displayMenu() {
        System.out.println("=== Order Menu ===");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Coffee     $50");
        System.out.println("0. Checkout");
        System.out.print("請輸入選項：");
    }

    /**
     * @param choice
     * @return
     */
    public static int getProductPrice(int choice) {
        switch (choice) {
            case 1: return 30;
            case 2: return 35;
            case 3: return 50;
            default: return -1;
        }
    }

    /**
     * @return
     */
    public static int getValidQuantity() {
        int quantity = 0;
        while (true) {
            System.out.print("請輸入數量：");
            quantity = scanner.nextInt();
            
            if (quantity > 0) {
                break;
            } else {
                System.out.println("數量必須大於 0，請重新輸入！");
            }
        }
        return quantity;
    }

    /**
     * @param price
     * @param quantity
     * @return
     */
    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    /**
     * @param totalItems
     * @param totalAmount
     */
    public static void printReceipt(int totalItems, int totalAmount) {
        System.out.println("\n=== Receipt ===");
        System.out.println("Total items: " + totalItems);
        System.out.println("Total amount: " + totalAmount);
    }
}