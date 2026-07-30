import java.util.Random;

public class AlgorithmComparisonReport {

    public static long selectionSort(int[] arr) {
        long comparisons = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
        return comparisons;
    }

    public static long insertionSort(int[] arr) {
        long comparisons = 0;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
        return comparisons;
    }

    private static long mergeSortComparisons = 0;

    public static long mergeSort(int[] arr) {
        mergeSortComparisons = 0;
        if (arr.length <= 1) return 0;
        runMergeSort(arr, 0, arr.length - 1);
        return mergeSortComparisons;
    }

    private static void runMergeSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        runMergeSort(arr, left, mid);
        runMergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            mergeSortComparisons++;
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        for (int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];
        }
    }

    public static void testAndPrint(int size) {
        int[] sorted = new int[size];
        int[] reversed = new int[size];
        int[] random = new int[size];

        Random rand = new Random(42);
        for (int i = 0; i < size; i++) {
            sorted[i] = i;
            reversed[i] = size - i;
            random[i] = rand.nextInt(size * 10);
        }

        System.out.println("=========================================================");
        System.out.printf(" 資料筆數 N = %d\n", size);
        System.out.println("=========================================================");
        System.out.printf("%-12s | %-12s | %-15s | %-15s\n", "資料狀態", "選擇排序", "插入排序", "歸併排序");
        System.out.println("---------------------------------------------------------");

        printRow("已排序 (Sorted)", sorted);
        printRow("反向 (Reversed)", reversed);
        printRow("固定亂序 (Random)", random);
        System.out.println();
    }

    private static void printRow(String label, int[] source) {
        long c1 = selectionSort(source.clone());
        long c2 = insertionSort(source.clone());
        long c3 = mergeSort(source.clone());
        System.out.printf("%-15s | %-15d | %-15d | %-15d\n", label, c1, c2, c3);
    }

    public static void main(String[] args) {
        System.out.println("=== 課後作業三：演算法比較報告實驗 ===");
        testAndPrint(16);
        testAndPrint(128);
        testAndPrint(1024);

        System.out.println("=== 實驗觀察結論 ===");
        System.out.println("1. 選擇排序 (Selection Sort)：比較次數恆為 N*(N-1)/2，不受資料初始狀態影響。");
        System.out.println("2. 插入排序 (Insertion Sort)：對已排序資料極度敏感，最佳時間複雜度達 O(N)；但在反向資料下需 N*(N-1)/2 次比較。");
        System.out.println("3. 歸併排序 (Merge Sort)：無論資料狀態為何，比較次數穩定維持在 O(N log N) 數量級，大幅優於 O(N^2) 演算法。");
    }
}