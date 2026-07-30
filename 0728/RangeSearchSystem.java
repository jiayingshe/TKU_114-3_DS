import java.util.Arrays;

public class RangeSearchSystem {

    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums == null || nums.length == 0) {
            return result;
        }

        result[0] = findBound(nums, target, true);
        if (result[0] != -1) {
            result[1] = findBound(nums, target, false);
        }

        return result;
    }

    private static int findBound(int[] nums, int target, boolean isFirst) {
        int low = 0;
        int high = nums.length - 1;
        int boundIndex = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                boundIndex = mid;
                if (isFirst) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return boundIndex;
    }

    public static void printRangeResult(int[] nums, int target) {
        System.out.println("----------------------------------------");
        System.out.println("原始陣列: " + Arrays.toString(nums));
        System.out.println("搜尋目標: " + target);

        int[] range = searchRange(nums, target);
        System.out.println("回傳索引範圍: " + Arrays.toString(range));

        if (range[0] == -1) {
            System.out.println("結果：找不到目標值，出現次數 0 次。");
        } else {
            int count = range[1] - range[0] + 1;
            System.out.println("第一個位置 (First Index) : " + range[0]);
            System.out.println("最後位置 (Last Index)    : " + range[1]);
            System.out.println("總共出現次數             : " + count + " 次");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== 課後作業五：第一筆與最後記分位置 ===");

        int[] sortedScores = {57, 60, 75, 85, 85, 85, 85, 92, 98};

        printRangeResult(sortedScores, 85);

        printRangeResult(sortedScores, 75);

        printRangeResult(sortedScores, 100);

        printRangeResult(new int[]{}, 85);
    }
}