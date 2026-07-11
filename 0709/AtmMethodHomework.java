import java.util.Scanner;
public class AtmMethodHomework {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int balance = 1000;
        boolean running = true;
        while (running) {
            printMenu();
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    printBalance(balance);
                    break;
                case 2:
                    int depAmount = readPositiveAmount(sc, "請輸入存款金額：");
                    balance = deposit(balance, depAmount);
                    break;
                case 3:
                    int witAmount = readPositiveAmount(sc, "請輸入提款金額：");
                    balance = withdraw(balance, witAmount);
                    break;
                case 0:
                    System.out.println("感謝使用，謝謝光臨！");
                    running = false;
                    break;
                default:
                    System.out.println("無效的選項，請重新輸入！\n");
                    break;
            }
        }
        sc.close();
    }
    public static void printMenu() {
        System.out.println("=== ATM Menu ===");
        System.out.println("1：查詢餘額");
        System.out.println("2：存款");
        System.out.println("3：提款");
        System.out.println("0：離開");
        System.out.print("請輸入選項：");
    }

    /**
     * @param sc
     * @param message
     * @return
     */
    public static int readPositiveAmount(Scanner sc, String message) {
        int amount = 0;
        while (true) {
            System.out.print(message);
            amount = sc.nextInt();
            if (amount > 0) {
                break;
            } else {
                System.out.println("錯誤：金額必須大於 0 元！\n");
            }
        }
        return amount;
    }

    /**
     * @param balance
     * @param amount
     * @return
     */
    public static int deposit(int balance, int amount) {
        balance += amount;
        System.out.println("存款成功！已存入 " + amount + " 元，目前餘額：" + balance + " 元\n");
        return balance;
    }

    /**
     * @param balance
     * @param amount
     * @return
     */
    public static int withdraw(int balance, int amount) {
        if (amount > balance) {
            System.out.println("錯誤：餘額不足！目前餘額僅有 " + balance + " 元\n");
        } else {
            balance -= amount;
            System.out.println("提款成功！已領出 " + amount + " 元，目前餘額：" + balance + " 元\n");
        }
        return balance;
    }

    /**
     * @param balance
     */
    public static void printBalance(int balance) {
        System.out.println("目前餘額為：" + balance + " 元\n");
    }
}