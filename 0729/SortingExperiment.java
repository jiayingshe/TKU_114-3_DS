
public class SortingExperiment {

    static class Metrics {
        long comparisons = 0;
        long swaps = 0;
        long moves = 0;

        void reset() {
            comparisons = 0;
            swaps = 0;
            moves = 0;
        }
    }

    public static void selectionSort(int[] arr, Metrics m) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                m.comparisons++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
                m.swaps++;
                m.moves += 3;
            }
        }
    }

    public static void insertionSort(int[] arr, Metrics m) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            m.moves++;
            int j = i - 1;

            while (j >= 0) {
                m.comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    m.moves++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
            m.moves++;
        }
    }

    public static void runExperiment(String dataType, int[] original) {
        System.out.println("==================================================");
        System.out.println("資料型態：" + dataType + " (資料筆數: " + original.length + ")");
        System.out.println("--------------------------------------------------");

        Metrics m1 = new Metrics();
        int[] arr1 = original.clone();
        selectionSort(arr1, m1);

        Metrics m2 = new Metrics();
        int[] arr2 = original.clone();
        insertionSort(arr2, m2);

        System.out.printf("%-12s | %-12s | %-12s | %-12s\n", "演算法", "比較次數", "交換次數", "移動次數");
        System.out.println("--------------------------------------------------");
        System.out.printf("%-12s | %-14d | %-14d | %-14d\n", "選擇排序", m1.comparisons, m1.swaps, m1.moves);
        System.out.printf("%-12s | %-14d | %-14d | %-14d\n", "插入排序", m2.comparisons, m2.swaps, m2.moves);
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== 課後作業三：排序操作統計實驗 ===");

        int size = 100;
        int[] sortedData = new int[size];
        int[] reversedData = new int[size];
        int[] randomData = new int[size];

        for (int i = 0; i < size; i++) {
            sortedData[i] = i + 1;
            reversedData[i] = size - i;
            randomData[i] = (int) (Math.random() * 1000);
        }

        runExperiment("已排序資料 (Best Case)", sortedData);
        runExperiment("逆向排序資料 (Worst Case)", reversedData);
        runExperiment("隨機排列資料 (Average Case)", randomData);

        System.out.println("=== 觀察結論 ===");
        System.out.println("1. 選擇排序 (Selection Sort)：");
        System.out.println("   - 無論輸入資料的狀態為何，比較次數皆固定為 N*(N-1)/2 次。");
        System.out.println("   - 交換次數極少（最多 N-1 次），適合寫入成本高的儲存介質。");
        System.out.println("2. 插入排序 (Insertion Sort)：");
        System.out.println("   - 對於「已排序資料」表現極佳，比較次數僅需要 N-1 次，無任何移動。");
        System.out.println("   - 在「逆向排序」時表現最差，移動與比較次數皆達到 O(N^2)。");
        System.out.println("3. 綜合評估：");
        System.out.println("   - 對於接近排序完成的資料，插入排序具有絕對優勢且具備穩定性 (Stable)。");
    }
}