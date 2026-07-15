import java.util.Scanner;
public class TextAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = readValidInput(sc);
        System.out.println("原始字元數：" + text.length());
        String trimmed = text.trim();
        System.out.println("有效字元數 (trim後)：" + trimmed.length());
        String[] words = splitWords(trimmed);
        System.out.println("單字數量：" + words.length);
        System.out.println("英文字母母音總數：" + countVowels(trimmed));
        System.out.println("最長單字：" + findLongestWord(words));
        System.out.print("請輸入要搜尋的關鍵字：");
        String keyword = sc.nextLine();
        System.out.println("關鍵字 \"" + keyword + "\" 出現次數：" + countKeyword(words, keyword));
        sc.close();
    }
    public static String readValidInput(Scanner sc) {
        while (true) {
            System.out.print("請輸入一行非空白文字：");
            String input = sc.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                return input;
            }
            System.out.println("錯誤：輸入內容不可為空或全空白！");
        }
    }
    public static String[] splitWords(String text) {
        if (text.isEmpty()) return new String[0];
        return text.split(" +");
    }
    public static int countVowels(String text) {
        int count = 0;
        String lower = text.toLowerCase();
        for (int i = 0; i < lower.length(); i++) {
            char ch = lower.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }
    public static String findLongestWord(String[] words) {
        if (words.length == 0) return "";
        String longest = words[0];
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }
    public static int countKeyword(String[] words, String keyword) {
        int count = 0;
        for (String word : words) {
            if (word.equalsIgnoreCase(keyword)) {
                count++;
            }
        }
        return count;
    }
}