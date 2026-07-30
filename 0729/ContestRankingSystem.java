class Contestant {
    private String id;
    private String name;
    private int score;
    private double seconds;

    public Contestant(String id, String name, int score, double seconds) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.seconds = seconds;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getScore() { return score; }
    public double getSeconds() { return seconds; }

    @Override
    public String toString() {
        return String.format("編號: %-5s | 姓名: %-8s | 分數: %3d 分 | 完成秒數: %5.2f 秒", 
                             id, name, score, seconds);
    }
}

public class ContestRankingSystem {

    public static void insertionSort(Contestant[] contestants) {
        if (contestants == null || contestants.length <= 1) return;

        for (int i = 1; i < contestants.length; i++) {
            Contestant key = contestants[i];
            int j = i - 1;

            while (j >= 0 && shouldSwap(key, contestants[j])) {
                contestants[j + 1] = contestants[j];
                j--;
            }
            contestants[j + 1] = key;
        }
    }

    private static boolean shouldSwap(Contestant target, Contestant current) {
        if (target.getScore() > current.getScore()) {
            return true;
        } else if (target.getScore() == current.getScore()) {
            return target.getSeconds() < current.getSeconds();
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("=== 課後作業一：參賽者排名系統 ===");

        Contestant[] contestants = {
            new Contestant("C001", "張小明", 85, 45.2),
            new Contestant("C002", "李大華", 92, 50.1),
            new Contestant("C003", "王美麗", 85, 40.8),
            new Contestant("C004", "陳阿強", 92, 42.5),
            new Contestant("C005", "林小英", 78, 38.0)
        };

        System.out.println("\n【排序前原始資料】");
        for (Contestant c : contestants) {
            System.out.println(c);
        }

        insertionSort(contestants);

        System.out.println("\n【排序後最終排名結果】");
        for (int i = 0; i < contestants.length; i++) {
            System.out.printf("名次 %d | %s\n", (i + 1), contestants[i]);
        }
    }
}