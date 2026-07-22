import java.util.Scanner;

public class ContactBookSystem {
    private static Contact[] contacts = new Contact[100];
    private static int contactCount = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("請選擇操作項目 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addContact();
                    break;
                case "2":
                    searchContact();
                    break;
                case "3":
                    updatePhone();
                    break;
                case "4":
                    deleteContact();
                    break;
                case "5":
                    listAllContacts();
                    break;
                case "6":
                    running = false;
                    System.out.println("感謝使用聯絡人管理系統，再見！");
                    break;
                default:
                    System.out.println("輸入無效，請選擇 1 到 6 之中的選項。");
            }
            System.out.println();
        }
    }

    public static void printMenu() {
        System.out.println("=== 聯絡人管理系統 ===");
        System.out.println("1. 新增聯絡人");
        System.out.println("2. 搜尋聯絡人");
        System.out.println("3. 修改電話");
        System.out.println("4. 刪除聯絡人");
        System.out.println("5. 列出所有聯絡人");
        System.out.println("6. 離開系統");
        System.out.println("======================");
    }

    public static void addContact() {
        if (contactCount >= contacts.length) {
            System.out.println("失敗：通訊錄容量已滿！");
            return;
        }

        System.out.print("請輸入代碼: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("失敗：代碼不可為空白！");
            return;
        }
        if (findContactIndexById(id) != -1) {
            System.out.println("失敗：代碼 [" + id + "] 已存在，不可重複！");
            return;
        }

        System.out.print("請輸入姓名: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("失敗：姓名不可為空白！");
            return;
        }

        System.out.print("請輸入電話: ");
        String phone = scanner.nextLine().trim();

        System.out.print("請輸入 Email: ");
        String email = scanner.nextLine().trim();

        contacts[contactCount] = new Contact(id, name, phone, email);
        contactCount++;
        System.out.println("成功：已新增聯絡人 " + name);
    }

    public static void searchContact() {
        System.out.print("請輸入要搜尋的代碼或姓名關鍵字: ");
        String keyword = scanner.nextLine().trim();
        if (keyword.isEmpty()) {
            System.out.println("提示：搜尋關鍵字不可為空白！");
            return;
        }

        boolean found = false;
        for (int i = 0; i < contactCount; i++) {
            if (contacts[i].getId().equalsIgnoreCase(keyword)
                    || contacts[i].getName().contains(keyword)) {
                System.out.println(contacts[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("查無符合 [" + keyword + "] 的聯絡人。");
        }
    }

    public static void updatePhone() {
        System.out.print("請輸入要修改電話的聯絡人代碼: ");
        String id = scanner.nextLine().trim();
        int index = findContactIndexById(id);

        if (index == -1) {
            System.out.println("失敗：找不到代碼為 [" + id + "] 的聯絡人。");
            return;
        }

        System.out.print("請輸入新電話: ");
        String newPhone = scanner.nextLine().trim();
        if (newPhone.isEmpty()) {
            System.out.println("失敗：新電話不可為空白！");
            return;
        }

        contacts[index].setPhone(newPhone);
        System.out.println("成功：已更新 " + contacts[index].getName() + " 的電話。");
    }

    public static void deleteContact() {
        System.out.print("請輸入要刪除的聯絡人代碼: ");
        String id = scanner.nextLine().trim();
        int index = findContactIndexById(id);

        if (index == -1) {
            System.out.println("失敗：找不到代碼為 [" + id + "] 的聯絡人。");
            return;
        }

        String targetName = contacts[index].getName();
        for (int i = index; i < contactCount - 1; i++) {
            contacts[i] = contacts[i + 1];
        }
        contacts[contactCount - 1] = null;
        contactCount--;

        System.out.println("成功：已刪除聯絡人 " + targetName);
    }

    public static void listAllContacts() {
        if (contactCount == 0) {
            System.out.println("目前通訊錄內沒有任何聯絡人。");
            return;
        }
        System.out.println("--- 聯絡人清單 (共 " + contactCount + " 筆) ---");
        for (int i = 0; i < contactCount; i++) {
            System.out.println((i + 1) + ". " + contacts[i]);
        }
    }

    private static int findContactIndexById(String id) {
        if (id == null || id.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < contactCount; i++) {
            if (contacts[i].getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }
}