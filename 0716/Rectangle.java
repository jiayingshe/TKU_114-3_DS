public class Rectangle {
    private double width;
    private double height;
    public Rectangle(double width, double height) {
        setWidth(width);
        setHeight(height);
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        if (width > 0) {
            this.width = width;
        } else {
            System.out.println("錯誤：寬度必須大於 0！設為預設值 1.0");
            this.width = 1.0;
        }
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        if (height > 0) {
            this.height = height;
        } else {
            System.out.println("錯誤：高度必須大於 0！設為預設值 1.0");
            this.height = 1.0;
        }
    }
    public double calculateArea() {
        return width * height;
    }
    public double calculatePerimeter() {
        return 2 * (width + height);
    }
    public boolean isSquare() {
        return width == height;
    }
    @Override
    public String toString() {
        return "Rectangle [Width = " + width + ", Height = " + height + "]";
    }
}