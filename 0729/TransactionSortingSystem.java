class Transaction {
    private String txnId;
    private String account;
    private double amount;
    private long timestamp;

    public Transaction(String txnId, String account, double amount, long timestamp) {
        this.txnId = txnId;
        this.account = account;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getTxnId() { return txnId; }
    public String getAccount() { return account; }
    public double getAmount() { return amount; }
    public long getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("交易單號: %-6s | 帳戶: %-10s | 金額: $%9.2f | 時間序號: %d", 
                             txnId, account, amount, timestamp);
    }
}

public class TransactionSortingSystem {

    public static void sortTransactions(Transaction[] txns) {
        if (txns == null || txns.length <= 1) return;

        int n = txns.length;
        for (int i = 1; i < n; i++) {
            Transaction key = txns[i];
            int j = i - 1;

            while (j >= 0 && shouldPrecede(key, txns[j])) {
                txns[j + 1] = txns[j];
                j--;
            }
            txns[j + 1] = key;
        }
    }

    private static boolean shouldPrecede(Transaction target, Transaction current) {
        if (target.getAmount() > current.getAmount()) {
            return true;
        } else if (target.getAmount() == current.getAmount()) {
            return target.getTimestamp() < current.getTimestamp();
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("=== 課後作業四：交易記錄排序系統 ===");

        Transaction[] transactions = {
            new Transaction("TX101", "Acc-A", 15000.0, 1620000010L),
            new Transaction("TX102", "Acc-B", 50000.0, 1620000005L),
            new Transaction("TX103", "Acc-C", 15000.0, 1620000001L),
            new Transaction("TX104", "Acc-D", 28000.0, 1620000020L),
            new Transaction("TX105", "Acc-E", 15000.0, 1620000003L)
        };

        System.out.println("\n【排序前交易記錄】");
        for (Transaction t : transactions) {
            System.out.println(t);
        }

        sortTransactions(transactions);

        System.out.println("\n【排序後領先結果 (金額降冪 / 時間升冪)】");
        for (int i = 0; i < transactions.length; i++) {
            System.out.printf("排名 %d | %s\n", (i + 1), transactions[i]);
        }
    }
}