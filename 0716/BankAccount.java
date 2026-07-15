public class BankAccount {
    private String accountNumber;
    private String accountName;
    private int balance;
    public BankAccount(String accountNumber, String accountName, int balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        if (balance >= 0) {
            this.balance = balance;
        } else {
            System.out.println("錯誤：開戶餘額不可小於 0，設為 0 元。");
            this.balance = 0;
        }
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public String getAccountName() {
        return accountName;
    }
    public int getBalance() {
        return balance;
    }
    public boolean deposit(int amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }
    public boolean withdraw(int amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            return true;
        }
        return false;
    }
    public boolean transferTo(BankAccount target, int amount) {
        if (amount > 0 && amount <= this.balance && target != null) {
            this.balance -= amount;
            target.deposit(amount);
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return "Account [" + accountNumber + " - " + accountName + "] Balance: $" + balance;
    }
}