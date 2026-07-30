import java.util.ArrayList;
import java.util.List;

public class BookAlgorithms {

    public static void mergeSortByIdAsc(List<Book> books, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortByIdAsc(books, left, mid);
        mergeSortByIdAsc(books, mid + 1, right);
        mergeByIdAsc(books, left, mid, right);
    }

    private static void mergeByIdAsc(List<Book> books, int left, int mid, int right) {
        List<Book> temp = new ArrayList<>();
        int i = left, j = mid + 1;
        while (i <= mid && j <= right) {
            if (books.get(i).getId() <= books.get(j).getId()) {
                temp.add(books.get(i++));
            } else {
                temp.add(books.get(j++));
            }
        }
        while (i <= mid) temp.add(books.get(i++));
        while (j <= right) temp.add(books.get(j++));
        for (int k = 0; k < temp.size(); k++) {
            books.set(left + k, temp.get(k));
        }
    }

    public static void mergeSortByBorrowCountDesc(List<Book> books, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortByBorrowCountDesc(books, left, mid);
        mergeSortByBorrowCountDesc(books, mid + 1, right);
        mergeByBorrowCountDesc(books, left, mid, right);
    }

    private static void mergeByBorrowCountDesc(List<Book> books, int left, int mid, int right) {
        List<Book> temp = new ArrayList<>();
        int i = left, j = mid + 1;
        while (i <= mid && j <= right) {
            if (books.get(i).getBorrowCount() >= books.get(j).getBorrowCount()) {
                temp.add(books.get(i++));
            } else {
                temp.add(books.get(j++));
            }
        }
        while (i <= mid) temp.add(books.get(i++));
        while (j <= right) temp.add(books.get(j++));
        for (int k = 0; k < temp.size(); k++) {
            books.set(left + k, temp.get(k));
        }
    }

    public static Book binarySearchById(List<Book> books, int targetId) {
        int left = 0, right = books.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (books.get(mid).getId() == targetId) {
                return books.get(mid);
            } else if (books.get(mid).getId() < targetId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static Book sequentialSearchByTitle(List<Book> books, String targetTitle) {
        for (Book b : books) {
            if (b.getTitle().equals(targetTitle)) {
                return b;
            }
        }
        return null;
    }
}