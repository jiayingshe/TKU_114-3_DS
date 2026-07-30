import java.util.ArrayList;
import java.util.List;

public class LibraryManagementSystem {
    private List<Book> bookList = new ArrayList<>();

    public boolean addBook(Book book) {
        if (book == null) return false;
        for (Book b : bookList) {
            if (b.getId() == book.getId()) {
                System.out.println("錯誤：書籍編號 " + book.getId() + " 已存在！");
                return false;
            }
        }
        bookList.add(book);
        return true;
    }

    public void displayAllBooks() {
        if (bookList.isEmpty()) {
            System.out.println("（無書籍資料）");
            return;
        }
        for (Book b : bookList) {
            System.out.println(b);
        }
    }

    public void sortByIdAsc() {
        if (bookList.isEmpty()) return;
        BookAlgorithms.mergeSortByIdAsc(bookList, 0, bookList.size() - 1);
    }

    public void sortByBorrowCountDesc() {
        if (bookList.isEmpty()) return;
        BookAlgorithms.mergeSortByBorrowCountDesc(bookList, 0, bookList.size() - 1);
    }

    public Book findById(int id) {
        if (bookList.isEmpty()) return null;
        sortByIdAsc();
        return BookAlgorithms.binarySearchById(bookList, id);
    }

    public Book findByTitle(String title) {
        if (bookList.isEmpty()) return null;
        return BookAlgorithms.sequentialSearchByTitle(bookList, title);
    }

    public static void main(String[] args) {
        LibraryManagementSystem sys = new LibraryManagementSystem();

        System.out.println("=== 測試 1：空資料查詢 ===");
        System.out.println("二分搜尋 ID 101: " + sys.findById(101));

        System.out.println("\n=== 測試 2：新增書籍與處理重複編號 ===");
        sys.addBook(new Book(105, "Java 程式設計", "資訊", 120));
        sys.addBook(new Book(102, "資料結構", "資訊", 350));
        sys.addBook(new Book(108, "演算法概論", "資訊", 200));
        sys.addBook(new Book(101, "作業系統", "資訊", 500));
        sys.addBook(new Book(102, "重複的書", "其他", 10));

        System.out.println("\n【原始書籍清單】");
        sys.displayAllBooks();

        System.out.println("\n=== 測試 3：依編號升冪排序 (Merge Sort) ===");
        sys.sortByIdAsc();
        sys.displayAllBooks();

        System.out.println("\n=== 測試 4：依借閱次數降冪排序 (Merge Sort) ===");
        sys.sortByBorrowCountDesc();
        sys.displayAllBooks();

        System.out.println("\n=== 測試 5：二分查找（依編號 102） ===");
        Book foundById = sys.findById(102);
        System.out.println("搜尋結果: " + (foundById != null ? foundById : "找不到資料"));

        System.out.println("\n=== 測試 6：順序搜尋（依書名 '演算法概論' 與不存在的書） ===");
        Book foundByTitle = sys.findByTitle("演算法概論");
        System.out.println("搜尋結果 1: " + (foundByTitle != null ? foundByTitle : "找不到資料"));
        Book notFound = sys.findByTitle("不存在的書");
        System.out.println("搜尋結果 2: " + (notFound != null ? notFound : "找不到資料"));
    }
}