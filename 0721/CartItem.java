public class CartItem {
    private String id;
    private String name;
    private double price;
    private int quantity;

    public CartItem(String id, String name, double price, int quantity) {
        this.id = (id != null) ? id.trim() : "";
        this.name = (name != null) ? name.trim() : "";
        this.price = Math.max(price, 0.0);
        this.quantity = Math.max(quantity, 1);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        }
    }

    public void addQuantity(int amount) {
        if (amount > 0) {
            this.quantity += amount;
        }
    }

    public double getTotalPrice() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return "代碼: " + id + " | 名稱: " + name + " | 單價: $" + price + " | 數量: " + quantity + " | 小計: $" + getTotalPrice();
    }
}