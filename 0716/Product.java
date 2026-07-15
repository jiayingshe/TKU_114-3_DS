public class Product {
    private String name;
    private int price;
    private int stock;
    public Product(String name, int price, int stock) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("錯誤：品名不可為空白！");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("錯誤：價格必須大於 0！");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("錯誤：庫存不得小於 0！");
        }
        this.name = name.trim();
        this.price = price;
        this.stock = stock;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public int getStock() {
        return stock;
    }
    public void setPrice(int price) {
        if (price <= 0) {
            System.out.println("錯誤：價格必須大於 0，修改失敗！");
            return;
        }
        this.price = price;
    }
    public void restock(int amount) {
        if (amount <= 0) {
            System.out.println("錯誤：補貨數量必須大於 0，補貨失敗！");
            return;
        }
        this.stock += amount;
    }
    public boolean sell(int amount) {
        if (amount <= 0) {
            System.out.println("錯誤：購買數量必須大於 0！");
            return false;
        }
        if (amount > this.stock) {
            System.out.println("錯誤：庫存不足，剩餘庫存：" + this.stock);
            return false;
        }
        this.stock -= amount;
        return true;
    }
    public boolean isLowStock() {
        return this.stock < 10;
    }
    public int getInventoryValue() {
        return this.price * this.stock;
    }
    @Override
    public String toString() {
        return String.format("%-12s\t%d\t%d", name, price, stock);
    }
}