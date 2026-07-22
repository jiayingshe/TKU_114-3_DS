import java.util.Scanner;

public class PlaylistSystem {
    public static void main(String[] args) {
        PlaylistLinkedList playlist = new PlaylistLinkedList();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("=== 播放清單管理系統 ===");
            System.out.println("1. 尾端新增歌曲");
            System.out.println("2. 依代碼搜尋歌曲");
            System.out.println("3. 依代碼刪除歌曲");
            System.out.println("4. 印出完整播放順序");
            System.out.println("5. 離開系統");
            System.out.print("請選擇操作 (1-5): ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.print("請輸入歌曲代碼: ");
                    String id = scanner.nextLine();
                    System.out.print("請輸入歌曲名稱: ");
                    String title = scanner.nextLine();
                    if (playlist.addLast(id, title)) {
                        System.out.println("成功：已加入歌曲 [" + title.trim() + "]");
                    } else {
                        System.out.println("失敗：代碼/名稱不可空白，或歌曲代碼已存在！");
                    }
                    break;
                case "2":
                    System.out.print("請輸入要搜尋的歌曲代碼: ");
                    String searchId = scanner.nextLine();
                    PlaylistNode found = playlist.findById(searchId);
                    if (found != null) {
                        System.out.println("搜尋結果：[" + found.id + "] " + found.title);
                    } else {
                        System.out.println("查無代碼為 [" + searchId.trim() + "] 的歌曲。");
                    }
                    break;
                case "3":
                    System.out.print("請輸入要刪除的歌曲代碼: ");
                    String removeId = scanner.nextLine();
                    if (playlist.removeById(removeId)) {
                        System.out.println("成功：已刪除代碼 [" + removeId.trim() + "] 的歌曲。");
                    } else {
                        System.out.println("失敗：播放清單為空，或找不到該代碼。");
                    }
                    break;
                case "4":
                    playlist.printPlaylist();
                    break;
                case "5":
                    running = false;
                    System.out.println("感謝使用播放清單系統！");
                    break;
                default:
                    System.out.println("無效的選項，請重新選擇。");
            }
            System.out.println();
        }
        scanner.close();
    }
}