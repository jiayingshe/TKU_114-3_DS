import java.util.Scanner;
public class AtmSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int balance = 1000;
        int depCount = 0, witCount = 0;
        int totalDep = 0, totalWit = 0;
        boolean running = true;
        while (running) {
            printMenu();
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    printBalance(balance);
                    break;
                case 2:
                    int depAmount = readPositiveAmount(sc, "請輸入存款金額：");
                    balance = deposit(balance, depAmount);
                    depCount++;
                    totalDep += depAmount;
                    break;
                case 3:
                    int witAmount = readPositiveAmount(sc, "請輸入提款金額：");
                    if (canWithdraw(balance, witAmount)) {
                        balance = withdraw(balance, witAmount);
                        witCount++;
                        totalWit += witAmount;
                    } else {
                        System.out.println("錯誤：餘額不足，提款失敗！\n");
                    }
                    break;
                case 4:
                    printSummary(balance, depCount, witCount, totalDep, totalWit);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("無效的選項，請重新輸入！\n");
                    break;
            }
        }
        printSummary(balance, depCount, witCount, totalDep, totalWit);
        sc.close();
    }
    public static void printMenu() {
        System.out.println("=== ATM Menu ===");
        System.out.println("1. Check balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Show summary");
        System.out.println("0. Exit");
        System.out.print("請輸入選項：");
    }
    public static int readPositiveAmount(Scanner sc, String message) {
        int amount = 0;
        while (true) {
            System.out.print(message);
            amount = sc.nextInt();
            if (amount > 0) break;
            System.out.println("錯誤：金額必須大於 0 元！\n");
        }
        return amount;
    }
    public static int deposit(int balance, int amount) {
        balance += amount;
        System.out.println("Balance: " + balance + "\n");
        return balance;
    }
    public static int withdraw(int balance, int amount) {
        balance -= amount;
        System.out.println("Balance: " + balance + "\n");
        return balance;
    }
    public static boolean canWithdraw(int balance, int amount) {
        return amount <= balance;
    }
    public static void printBalance(int balance) {
        System.out.println("Current Balance: " + balance + "\n");
    }
    public static void printSummary(int balance, int depositCount, int withdrawCount, int totalDeposit, int totalWithdraw) {
        System.out.println("\n=== ATM Summary ===");
        System.out.println("Final balance: " + balance);
        System.out.println("Deposit count: " + depositCount);
        System.out.println("Withdraw count: " + withdrawCount);
        System.out.println("Total deposit: " + totalDeposit);
        System.out.println("Total withdraw: " + totalWithdraw);
        System.out.println();
    }
}