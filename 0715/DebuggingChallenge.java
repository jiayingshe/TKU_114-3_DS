/*
=== 除錯挑戰報告 (Debugging Report) ===
1. 陣列越界錯誤 (ArrayIndexOutOfBoundsException)
   - 位置：for (int i = 0; i <= scores.length; i++)
   - 原因：i 變成 3 時超出 scores 的有效索引範圍 (0~2)。
   - 修正：將 <= 改為 <。
   - 斷點觀測值：當 i = 3 時，拋出例外。

2. 整數除法邏輯錯誤 (Integer Division)
   - 位置：double average = total / scores.length;
   - 原因：total 與長度皆為 int，計算結果會被無條件捨去小數點。
   - 修正：將 total 強制轉型為 double，改為 (double) total / scores.length。
   - 斷點觀測值：total = 247, scores.length = 3, 轉型前為 82.00，轉型後為 82.33。

3. Scanner 換行殘留問題 (Scanner Buffer Bug)
   - 位置：int age = sc.nextInt(); 之後
   - 原因：nextInt() 只讀取數字，把換行符號 (\n) 留在緩衝區，導致接下來的 nextLine() 直接讀到空字串而跳過。
   - 修正：在讀取指令前插入一行 sc.nextLine(); 來吃掉殘留的換行符。

4. 字串比較錯誤 (String Comparison Bug)
   - 位置：if (command == "exit")
   - 原因：在 Java 中 == 比較的是字串的記憶體位址，而非內容。
   - 修正：改用 command.equals("exit")。
   - 斷點觀測值：command 的字串內容為 "exit"，但與常數 "exit" 的位址不同。

5. 編譯錯誤 (Syntax Error)
   - 位置：System.out.println("系統結束，年齡：" + age)
   - 原因：結尾漏掉分號。
   - 修正：補上分號。
*/
import java.util.Scanner;
public class DebuggingChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] scores = {80, 75, 92};
        int total = 0;
        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }
        double average = (double) total / scores.length;
        System.out.printf("平均：%.2f%n", average);
        System.out.print("請輸入年齡：");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("請輸入指令：");
        String command = sc.nextLine();
        if (command.equals("exit")) {
            System.out.println("系統結束，年齡：" + age);
        }
        sc.close();
    }
}