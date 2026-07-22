import java.util.Stack;

public class TextEditorUndoSystem {
    private StringBuilder text;
    private Stack<String> history;
    public TextEditorUndoSystem() {
        this.text = new StringBuilder();
        this.history = new Stack<>();
    }
    public void append(String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        history.push(text.toString());
        text.append(str);
    }
    public void deleteLast(int count) {
        if (count <= 0 || text.length() == 0) {
            return;
        }
        history.push(text.toString());
        int deleteLength = Math.min(count, text.length());
        text.delete(text.length() - deleteLength, text.length());
    }
    public boolean undo() {
        if (history.isEmpty()) {
            System.out.println("Undo 失敗：沒有歷史紀錄可供復原！");
            return false;
        }
        text = new StringBuilder(history.pop());
        System.out.println("Undo 成功！");
        return true;
    }
    public void printText() {
        System.out.println("目前文字內容：\"" + text.toString() + "\"");
    }
    public static void main(String[] args) {
        TextEditorUndoSystem editor = new TextEditorUndoSystem();
        System.out.println("--- 初始狀態 ---");
        editor.printText();
        System.out.println("--- 操作 1: 嘗試無歷史時 Undo ---");
        editor.undo();
        System.out.println("--- 操作 2: 新增 \"Hello\" ---");
        editor.append("Hello");
        editor.printText();
        System.out.println("--- 操作 3: 新增 \" World\" ---");
        editor.append(" World");
        editor.printText();
        System.out.println("--- 操作 4: 新增 \"!!!\" ---");
        editor.append("!!!");
        editor.printText();
        System.out.println("--- 操作 5: 刪除最後 3 個字元 ---");
        editor.deleteLast(3);
        editor.printText();
        System.out.println("--- 操作 6: 連續 Undo 第 1 次 (復原刪除) ---");
        editor.undo();
        editor.printText();
        System.out.println("--- 操作 7: 連續 Undo 第 2 次 (復原 \"!!!\") ---");
        editor.undo();
        editor.printText();
        System.out.println("--- 操作 8: 連續 Undo 第 3 次 (復原 \" World\") ---");
        editor.undo();
        editor.printText();
        System.out.println("--- 操作 9: 連續 Undo 第 4 次 (復原 \"Hello\") ---");
        editor.undo();
        editor.printText();
        System.out.println("--- 操作 10: 再次 Undo (無歷史) ---");
        editor.undo();
    }
}