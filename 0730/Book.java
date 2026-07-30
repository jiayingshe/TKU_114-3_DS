public class Book {
    private int id;
    private String title;
    private String category;
    private int borrowCount;

    public Book(int id, String title, String category, int borrowCount) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.borrowCount = borrowCount;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public int getBorrowCount() { return borrowCount; }

    @Override
    public String toString() {
        return String.format("編號: %-4d | 書名: %-15s | 分類: %-8s | 借閱次數: %d", id, title, category, borrowCount);
    }
}