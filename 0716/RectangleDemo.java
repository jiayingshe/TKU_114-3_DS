public class RectangleDemo {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(5.0, 5.0);
        Rectangle r2 = new Rectangle(4.0, 6.5);
        Rectangle r3 = new Rectangle(-2.0, 8.0);
        System.out.println("=== 矩形 1 測試 ===");
        System.out.println(r1.toString());
        System.out.println("面積：" + r1.calculateArea());
        System.out.println("周長：" + r1.calculatePerimeter());
        System.out.println("是否為正方形：" + r1.isSquare());
        System.out.println("\n=== 矩形 2 測試 ===");
        System.out.println(r2.toString());
        System.out.println("面積：" + r2.calculateArea());
        System.out.println("周長：" + r2.calculatePerimeter());
        System.out.println("是否為正方形：" + r2.isSquare());
        System.out.println("\n=== 矩形 3 測試 (含錯誤輸入防呆) ===");
        System.out.println(r3.toString());
        System.out.println("面積：" + r3.calculateArea());
        System.out.println("周長：" + r3.calculatePerimeter());
        System.out.println("是否為正方形：" + r3.isSquare());
    }
}