import java.util.Scanner;
public class DrinkOrderSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int blackCount = 0, greenCount = 0, milkCount = 0, coffeeCount = 0;
        int totalItems = 0, totalAmount = 0;
        boolean running = true;
        while (running) {
            printMenu();
            int option = sc.nextInt();
            if (option == 0) {
                running = false;
                continue;
            }
            int price = getPrice(option);
            if (price == -1) {
                System.out.println("無效的選項，請重新輸入！\n");
                continue;
            }
            int quantity = readValidQuantity(sc);
            String itemName = getItemName(option);
            System.out.println(itemName + " x " + quantity);
            int subtotal = calculateSubtotal(price, quantity);
            System.out.println("Subtotal: " + subtotal + "\n");
            switch (option) {
                case 1: blackCount += quantity; break;
                case 2: greenCount += quantity; break;
                case 3: milkCount += quantity; break;
                case 4: coffeeCount += quantity; break;
            }
            totalItems += quantity;
            totalAmount += subtotal;
        }
        int finalAmount = calculateDiscountedTotal(totalAmount);
        printReceipt(blackCount, greenCount, milkCount, coffeeCount, totalItems, totalAmount, finalAmount);
        sc.close();
    }
    public static void printMenu() {
        System.out.println("=== Drink Menu ===");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Milk tea   $45");
        System.out.println("4. Coffee     $50");
        System.out.println("0. Checkout");
        System.out.print("請輸入選項：");
    }
    public static int getPrice(int option) {
        switch (option) {
            case 1: return 30;
            case 2: return 35;
            case 3: return 45;
            case 4: return 50;
            default: return -1;
        }
    }
    public static String getItemName(int option) {
        switch (option) {
            case 1: return "Black tea";
            case 2: return "Green tea";
            case 3: return "Milk tea";
            case 4: return "Coffee";
            default: return "";
        }
    }
    public static int readValidQuantity(Scanner sc) {
        int quantity = 0;
        while (true) {
            System.out.print("請輸入數量：");
            quantity = sc.nextInt();
            if (quantity > 0) break;
            System.out.println("數量必須大於 0，請重新輸入！");
        }
        return quantity;
    }
    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }
    public static int calculateDiscountedTotal(int totalAmount) {
        if (totalAmount >= 300) {
            return (int) (totalAmount * 0.9);
        }
        return totalAmount;
    }
    public static void printReceipt(int blackTeaCount, int greenTeaCount, int milkTeaCount, int coffeeCount, int totalItems, int totalAmount, int finalAmount) {
        System.out.println("\n=== Receipt ===");
        System.out.println("Black tea: " + blackTeaCount);
        System.out.println("Green tea: " + greenTeaCount);
        System.out.println("Milk tea: " + milkTeaCount);
        System.out.println("Coffee: " + coffeeCount);
        System.out.println("Total items: " + totalItems);
        System.out.println("Original amount: " + totalAmount);
        String hasDiscount = (totalAmount >= 300) ? "Yes (10% OFF)" : "No";
        System.out.println("Discount: " + hasDiscount);
        System.out.println("Final amount: " + finalAmount);
    }
}