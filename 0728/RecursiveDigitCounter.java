public class RecursiveDigitCounter {
    public static int countDigit(int number, int target) {
        if (target < 0 || target > 9) {
            return 0;
        }

        if (number < 0) {
            number = -number;
        }

        if (number == 0) {
            return (target == 0) ? 1 : 0;
        }

        return countDigitHelper(number, target);
    }

    private static int countDigitHelper(int number, int target) {
        if (number == 0) {
            return 0;
        }

        int lastDigit = number % 10;
        int match = (lastDigit == target) ? 1 : 0;
        return match + countDigitHelper(number / 10, target);
    }

    public static void main(String[] args) {
        System.out.println("=== 課後作業一：遞回統計數字出現次數測試 ===");

        int[][] testCases = {
            {1223334, 3},
            {1223334, 5},
            {0, 0},
            {-88823, 8},
            {7000507, 0},
            {99999, 9}
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