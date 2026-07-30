
class StoreProduct {
    private String id;
    private String name;
    private int price;
    private int stock;

    public StoreProduct(String id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getStock() { return stock; }

    @Override
    public String toString() {
        return String.format("編號: %-5s | 商品: %-12s | 價格: %5d 元 | 庫存: %3d 個", 
                             id, name, price, stock);
    }
}

public class ProductSortingSystem {

    public enum SortMode {
        PRICE_ASC("價格", "升冪"),
        PRICE_DESC("價格", "降冪"),
        STOCK_DESC("庫存", "降冪");

        final String field;
        final String direction;

        SortMode(String field, String direction) {
            this.field = field;
            this.direction = direction;
        }
    }

    public static void selectionSort(StoreProduct[] products, SortMode mode) {
        if (products == null || products.length <= 1) return;

        int n = products.length;
        for (int i = 0; i < n - 1; i++) {
            int targetIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (isBetter(products[j], products[targetIdx], mode)) {
                    targetIdx = j;
                }
            }
            if (targetIdx != i) {
                StoreProduct temp = products[i];
                products[i] = products[targetIdx];
                products[targetIdx] = temp;
            }
        }
    }

    private static boolean isBetter(StoreProduct p1, StoreProduct p2, SortMode mode) {
        switch (mode) {
            case PRICE_ASC:
                return p1.getPrice() < p2.getPrice();
            case PRICE_DESC:
                return p1.getPrice() > p2.getPrice();
            case STOCK_DESC:
                return p1.getStock() > p2.getStock();
            default:
                return false;
        }
    }

    public static void displayAndSort(StoreProduct[] original, SortMode mode) {
        StoreProduct[] copy = new StoreProduct[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i];
        }

        selectionSort(copy, mode);

        System.out.println("==================================================");
        System.out.printf("【排序模式】排序欄位：%s | 排序方向：%s\n", mode.field, mode.direction);
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < copy.length; i++) {
            System.out.printf("位置 %2d | %s\n", (i + 1), copy[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== 課後作業二：商品排序選單 ===");

        StoreProduct[] products = {
            new StoreProduct("P01", "無線滑鼠", 650, 25),
            new StoreProduct("P02", "機械鍵盤", 2400, 10),
            new StoreProduct("P03", "27吋螢幕", 5800, 8),
            new StoreProduct("P04", "USB隨身碟", 300, 100),
            new StoreProduct("P05", "藍芽耳機", 1800, 15),
            new StoreProduct("P06", "電競喇叭", 3200, 5),
            new StoreProduct("P07", "網絡攝影機", 1200, 18),
            new StoreProduct("P08", "人體工學椅", 8900, 3),
            new StoreProduct("P09", "滑鼠墊", 150, 80),
            new StoreProduct("P10", "行動電源", 800, 40)
        };

        displayAndSort(products, SortMode.PRICE_ASC);
        displayAndSort(products, SortMode.PRICE_DESC);
        displayAndSort(products, SortMode.STOCK_DESC);
    }
}