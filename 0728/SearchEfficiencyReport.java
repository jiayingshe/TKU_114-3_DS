public class SearchEfficiencyReport {
    public static int linearSearchCount(int[] arr, int target) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            count++;
            if (arr[i] == target) {
                break;
            }
        }
        return count;
    }

    public static int binarySearchCount(int[] arr, int target) {
        int count = 0;
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            count++;
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                break;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return count;
    }

    public static void testSize(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (i + 1) * 2;
        }

        int first = arr[0];
        int last = arr[size - 1];
        int notFound = -1;

        System.out.println("================--------------------------------==");
        System.out.printf("資料筆數: %d 筆\n", size);
        System.out.println("================--------------------------------==");
        System.out.printf("%-12s | %-15s | %-15s\n", "搜尋目標", "循序搜尋比較次數", "二分搜尋比較次數");
        System.out.println("--------------------------------------------------");
        
        System.out.printf("%-12s | %-17d | %-15d\n", "第一筆 (" + first + ")", 
                          linearSearchCount(arr, first), binarySearchCount(arr, first));
        System.out.printf("%-12s | %-17d | %-15d\n", "最後一筆 (" + last + ")", 
                          linearSearchCount(arr, last), binarySearchCount(arr, last));
        System.out.printf("%-12s | %-17d | %-15d\n", "不存在 (" + notFound + ")", 
                          linearSearchCount(arr, notFound), binarySearchCount(arr, notFound));
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== 課後作業四：搜尋效率分析報告 ===");
        
        testSize(16);
        testSize(128);
        testSize(1024);

        System.out.println("=== 觀察結果與分析 ===");
        System.out.println("1. 循序搜尋 O(n)：");
        System.out.println("   - 最佳情況（第一筆）：只需 1 次比較。");
        System.out.println("   - 最差/不存在情況：需要 N 次比較。比較次數隨資料量呈「線性成長」。");
        System.out.println("2. 二分搜尋 O(log n)：");
        System.out.println("   - 最差情況的比較次數約為 log2(N) + 1。");
        System.out.println("   - 當資料從 16 成長到 1024 筆（成長 64 倍）時，二分搜尋的最差比較次數僅從 5 次微幅增加到 11 次。");
        System.out.println("3. 使用條件：");
        System.out.println("   - 二分搜尋極度高效，但「前提是資料必須預先排序」。");
        System.out.println("   - 若資料未排序且僅搜尋一次，循序搜尋可能是較佳選擇（免去 O(n log n) 的排序成本）。");
    }
}