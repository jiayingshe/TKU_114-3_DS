public class RecursiveDigitCounter {

    public static int countDigit(int number, int target) {
        // target 必須在 0 到 9 之間
        if (target < 0 || target > 9) {
            return 0;
        }

        // 處理負數
        if (number < 0) {
            number = -number;
        }

        // 基本情況：0 的特殊處理
        if (number == 0) {
            return (target == 0) ? 1 : 0;
        }

        return countDigitHelper(number, target);
    }

    private static int countDigitHelper(int number, int target) {
        // 基本情況：遞回終止
        if (number == 0) {
            return 0;
        }

        int lastDigit = number % 10;
        int match = (lastDigit == target) ? 1 : 0;

        // 遞回呼叫：縮小問題規模
        return match + countDigitHelper(number / 10, target);
    }

    public static void main(String[] args) {
        System.out.println("=== 課後作業一：遞回統計數字出現次數測試 ===");

        int[][] testCases = {
            {1223334, 3},   // 測試 1: 正常多個重複數字 (應為 3)
            {1223334, 5},   // 測試 2: 目標數字不存在 (應為 0)
            {0, 0},         // 測試 3: 數字為 0, 目標為 0 (應為 1)
            {-88823, 8},    // 測試 4: 負數測試 (應為 3)
            {7000507, 0},   // 測試 5: 多個 0 (應為 4)
            {99999, 9}      // 測試 6: 全部重複 (應為 5)
        };

        for (int i = 0; i < testCases.length; i++) {
            int num = testCases[i][0];
            int target = testCases[i][1];
            int result = countDigit(num, target);
            System.out.printf("測試 %d - 數字: %d, 目標: %d => 出現次數: %d\n", 
                              (i + 1), num, target, result);
        }
    }
}