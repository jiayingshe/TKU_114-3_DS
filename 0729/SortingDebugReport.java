import java.util.Arrays;

public class SortingDebugReport {

    public static void buggySelectionSort_InnerLoop(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = 0; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }
    }

    public static void buggyInsertionSort_NoKeySaved(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int j = i - 1;
            while (j >= 0 && arr[j] > arr[i]) {
                arr[j + 1] = arr[j];
                j--;
            }
        }
    }

    public static void buggyInsertionSort_WrongDirection(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] < key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void fixedSelectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
            }
        }
    }

    public static void fixedInsertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== 課後作業五：排序計畫調試與診斷報告 ===");

        int[] test1 = {5, 2, 4, 1, 3};
        System.out.println("\n--- 1. 診斷：內層範圍錯誤 (Selection Sort) ---");
        System.out.println("原始資料: " + Arrays.toString(test1));
        int[] bad1 = test1.clone();
        buggySelectionSort_InnerLoop(bad1);
        System.out.println("錯誤輸出: " + Arrays.toString(bad1));
        int[] fix1 = test1.clone();
        fixedSelectionSort(fix1);
        System.out.println("修復輸出: " + Arrays.toString(fix1));

        int[] test2 = {9, 3, 1, 7};
        System.out.println("\n--- 2. 診斷：Key 未儲存 (Insertion Sort) ---");
        System.out.println("原始資料: " + Arrays.toString(test2));
        int[] bad2 = test2.clone();
        buggyInsertionSort_NoKeySaved(bad2);
        System.out.println("錯誤輸出: " + Arrays.toString(bad2));
        int[] fix2 = test2.clone();
        fixedInsertionSort(fix2);
        System.out.println("修復輸出: " + Arrays.toString(fix2));

        int[] test3 = {12, 5, 8, 20, 1};
        System.out.println("\n--- 3. 診斷：比較方向錯誤 (期望升冪) ---");
        System.out.println("原始資料: " + Arrays.toString(test3));
        int[] bad3 = test3.clone();
        buggyInsertionSort_WrongDirection(bad3);
        System.out.println("錯誤輸出 (變成降冪): " + Arrays.toString(bad3));
        int[] fix3 = test3.clone();
        fixedInsertionSort(fix3);
        System.out.println("修復輸出 (正確升冪): " + Arrays.toString(fix3));
    }
}