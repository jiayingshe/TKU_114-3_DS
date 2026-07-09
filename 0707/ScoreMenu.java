import java.util.Scanner;
public class ScoreMenu {
        public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
        
                System.out.print("請輸入姓名：");
                String name = scanner.nextLine();
        
                System.out.print("請輸入 Java 成績：");
                double javaScore = scanner.nextDouble();
        
                System.out.print("請輸入 English 成績：");
                double englishScore = scanner.nextDouble();
        
                System.out.print("請輸入 Math 成績：");
                double mathScore = scanner.nextDouble();
        
                double average = (javaScore + englishScore + mathScore) / 3.0;
                String status = (average >= 60) ? "及格" : "不及格";

                String rank;
                if (average >= 90) {
                rank = "A";
                }
                else if (average >= 80) {
                        rank = "B";
                }
                else if (average >= 70) {
                        rank = "C";
                } else if (average >= 60) {
                        rank = "D";
                }
                else {
                        rank = "F";
                }
        
        int choice = -1;
        while (choice != 0) {
                System.out.println("\n--- 功能選單 ---");
                System.out.println("1：顯示平均分數");
                System.out.println("2：顯示及格狀態");
                System.out.println("3：顯示等第");
                System.out.println("0：離開");
                System.out.print("請輸入選項 (0-3)：");
                choice = scanner.nextInt();
        
                switch (choice) {
                case 1:
                        System.out.printf("%s 的平均分數為：%.2f 分\n", name, average);
                        break;
                case 2:
                        System.out.printf("%s 的及格狀態為： %s\n", name, status);
                        break;
                case 3:
                        System.out.printf("%s 的成績等第為： %s 等級\n", name, rank);
                        break;
                case 0:
                        System.out.println("系統已結束，謝謝");
                        break;
                default:
                        System.out.println("無效的選項，請重新輸入。");
                        break;
                }
        }
        
        scanner.close();
        }
}