import java.util.Scanner;

public class TaskLinkedListSystem {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("=== 工作項目管理系統 ===");
            System.out.println("1. 新增緊急工作 (加到前端)");
            System.out.println("2. 新增一般工作 (加到尾端)");
            System.out.println("3. 完成工作");
            System.out.println("4. 刪除工作");
            System.out.println("5. 列出未完成工作與統計");
            System.out.println("6. 離開系統");
            System.out.print("請選擇操作 (1-6): ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.print("請輸入工作代碼: ");
                    String urgentId = scanner.nextLine();
                    System.out.print("請輸入工作說明: ");
                    String urgentDesc = scanner.nextLine();
                    if (taskList.addFirst(urgentId, urgentDesc)) {
                        System.out.println("成功：已將緊急工作 [" + urgentDesc.trim() + "] 加到最前端！");
                    } else {
                        System.out.println("失敗：輸入不可空白，或工作代碼已存在！");
                    }
                    break;
                case "2":
                    System.out.print("請輸入工作代碼: ");
                    String normalId = scanner.nextLine();
                    System.out.print("請輸入工作說明: ");
                    String normalDesc = scanner.nextLine();
                    if (taskList.addLast(normalId, normalDesc)) {
                        System.out.println("成功：已將一般工作 [" + normalDesc.trim() + "] 加到尾端！");
                    } else {
                        System.out.println("失敗：輸入不可空白，或工作代碼已存在！");
                    }
                    break;
                case "3":
                    System.out.print("請輸入要完成的工作代碼: ");
                    String completeId = scanner.nextLine();
                    if (taskList.completeTask(completeId)) {
                        System.out.println("成功：已將工作代碼 [" + completeId.trim() + "] 標記為完成！");
                    } else {
                        System.out.println("失敗：找不到該工作代碼。");
                    }
                    break;
                case "4":
                    System.out.print("請輸入要刪除的工作代碼: ");
                    String removeId = scanner.nextLine();
                    if (taskList.removeById(removeId)) {
                        System.out.println("成功：已刪除工作代碼 [" + removeId.trim() + "]。");
                    } else {
                        System.out.println("失敗：清單為空，或找不到該工作代碼。");
                    }
                    break;
                case "5":
                    taskList.printPendingTasks();
                    break;
                case "6":
                    running = false;
                    System.out.println("感謝使用工作項目管理系統！");
                    break;
                default:
                    System.out.println("無效選項，請重新輸入。");
            }
            System.out.println();
        }
        scanner.close();
    }
}