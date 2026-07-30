import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class AllOccurrenceSearch {
    public static void searchAllOccurrences(int[] scores, int target) {
        System.out.println("----------------------------------------");
        System.out.println("搜尋目標分數: " + target);
        if (scores == null || scores.length == 0) {
            System.out.println("錯誤：資料庫為空！");
            return;
        }
        List<Integer> foundIndices = new ArrayList<>();
        int comparisons = 0;
        for (int i = 0; i < scores.length; i++) {
            comparisons++;
            if (scores[i] == target) {
                foundIndices.add(i);
            }
        }

        System.out.println("比較次數: " + comparisons);
        if (foundIndices.isEmpty()) {
            System.out.println("結果：未找到符合分數 " + target + " 的資料。");
        } else {
            System.out.println("出現次數: " + foundIndices.size());
            System.out.println("符合的索引位置: " + foundIndices);
        }
    }
    public static void main(String[] args) {
        System.out.println("=== 課後作業二：搜尋全部資料相同 ===");
        int[] scores = {85, 92, 76, 85, 90, 60, 85, 100};
        System.out.println("原始分數陣列: " + Arrays.toString(scores));
        searchAllOccurrences(scores, 85);
        searchAllOccurrences(scores, 92);
        searchAllOccurrences(scores, 59);
        searchAllOccurrences(new int[]{}, 85);
    }
}