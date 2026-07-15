public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount("123-456", "Amy", 5000);
        BankAccount acc2 = new BankAccount("789-012", "Bob", 2000);
        System.out.println("=== 初始狀態 ===");
        System.out.println(acc1);
        System.out.println(acc2);
        System.out.println("\n=== 存款與提款測試 ===");
        if (acc1.deposit(1000)) System.out.println("Amy 成功存款 $1000");
        if (acc2.withdraw(500)) System.out.println("Bob 成功提款 $500");
        System.out.println(acc1);
        System.out.println(acc2);
        System.out.println("\n=== 成功轉帳測試 ===");
        if (acc1.transferTo(acc2, 1500)) {
            System.out.println("Amy 成功轉帳 $1500 給 Bob");
        } else {
            System.out.println("Amy 轉帳給 Bob 失敗");
        }
        System.out.println(acc1);
        System.out.println(acc2);
        System.out.println("\n=== 失敗轉帳測試 (金額不足) ===");
        if (acc2.transferTo(acc1, 5000)) {
            System.out.println("Bob 成功轉帳 $5000 給 Amy");
        } else {
            System.out.println("轉帳失敗：Bob 餘額不足以轉帳 $5000");
        }
        System.out.println(acc1);
        System.out.println(acc2);
        System.out.println("\n=== 失敗轉帳測試 (無效金額) ===");
        if (acc1.transferTo(acc2, -500)) {
            System.out.println("Amy 成功轉帳 -$500 給 Bob");
        } else {
            System.out.println("轉帳失敗：金額必須大於 0");
        }
        System.out.println(acc1);
        System.out.println(acc2);
    }
}