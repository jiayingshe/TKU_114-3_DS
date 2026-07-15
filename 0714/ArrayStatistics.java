import java.util.Scanner;
public class ArrayStatistics {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = readCount(sc);
        int[] scores = new int[count];
        inputScores(sc, scores);
        System.out.println("\n--- 全部成績 ---");
        for (int i = 0; i < scores.length; i++) {
            System.out.print(scores[i] + (i == scores.length - 1 ? "" : ", "));
        }
        System.out.println("\n");
        int total = calculateTotal(scores);
        double average = (double) total / count;
        int max = findMax(scores);
        int min = findMin(scores);
        int passCount = countPass(scores);
        int failCount = count - passCount;
        System.out.println("--- 統計結果 ---");
        System.out.println("總分：" + total);
        System.out.println("平均：" + average);
        System.out.println("最高分：" + max);
        System.out.println("最低分：" + min);
        System.out.println("及格人數：" + passCount);
        System.out.println("不及格人數：" + failCount);
        System.out.print("\n請輸入欲搜尋的目標成績：");
        int target = sc.nextInt();
        int index = findIndex(scores, target);
        if (index != -1) {
            System.out.println("目標成績 " + target + " 第一次出現在索引：" + index);
        } else {
            System.out.println("找不到目標成績 " + target);
        }
        sc.close();
    }
    public static int readCount(Scanner sc) {
        int count = 0;
        while (true) {
            System.out.print("請輸入資料筆數 (1~50)：");
            count = sc.nextInt();
            if (count >= 1 && count <= 50) break;
            System.out.println("錯誤：筆數範圍必須在 1 到 50 之間！");
        }
        return count;
    }
    public static void inputScores(Scanner sc, int[] scores) {
        for (int i = 0; i < scores.length; i++) {
            while (true) {
                System.out.print("請輸入第 " + (i + 1) + " 筆成績 (0~100)：");
                int score = sc.nextInt();
                if (score >= 0 && score <= 100) {
                    scores[i] = score;
                    break;
                }
                System.out.println("錯誤：成績範圍必須在 0 到 100 之間！");
            }
        }
    }
    public static int calculateTotal(int[] scores) {
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return total;
    }
    public static int findMax(int[] scores) {
        int max = scores[0];
        for (int score : scores) {
            if (score > max) max = score;
        }
        return max;
    }
    public static int findMin(int[] scores) {
        int min = scores[0];
        for (int score : scores) {
            if (score < min) min = score;
        }
        return min;
    }
    public static int countPass(int[] scores) {
        int passCount = 0;
        for (int score : scores) {
            if (score >= 60) passCount++;
        }
        return passCount;
    }
    public static int findIndex(int[] scores, int target) {
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == target) return i;
        }
        return -1;
    }
}