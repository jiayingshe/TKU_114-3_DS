import java.util.Scanner;

public class CourseRegistrationSystem {
    private static Course[] courses = new Course[100];
    private static int courseCount = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("請選擇操作項目 (1-8): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addCourse();
                    break;
                case "2":
                    enrollCourse();
                    break;
                case "3":
                    dropCourse();
                    break;
                case "4":
                    deleteCourse();
                    break;
                case "5":
                    searchCourse();
                    break;
                case "6":
                    listAllCourses();
                    break;
                case "7":
                    showStatistics();
                    break;
                case "8":
                    running = false;
                    System.out.println("感謝使用選課管理系統，再見！");
                    break;
                default:
                    System.out.println("輸入無效，請選擇 1 到 8 之中的選項。");
            }
            System.out.println();
        }
    }

    public static void printMenu() {
        System.out.println("=== 選課管理系統 ===");
        System.out.println("1. 新增課程");
        System.out.println("2. 學生選課 (增加人數)");
        System.out.println("3. 學生退選 (減少人數)");
        System.out.println("4. 刪除課程");
        System.out.println("5. 搜尋課程");
        System.out.println("6. 列出所有課程");
        System.out.println("7. 檢視統計數據與額滿課程");
        System.out.println("8. 離開系統");
        System.out.println("====================");
    }

    public static void addCourse() {
        if (courseCount >= courses.length) {
            System.out.println("失敗：系統課程容量已滿！");
            return;
        }

        System.out.print("請輸入課程代碼: ");
        String code = scanner.nextLine().trim();
        if (code.isEmpty()) {
            System.out.println("失敗：課程代碼不可為空白！");
            return;
        }
        if (findCourseIndexByCode(code) != -1) {
            System.out.println("失敗：課程代碼 [" + code + "] 已存在！");
            return;
        }

        System.out.print("請輸入課程名稱: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("失敗：課程名稱不可為空白！");
            return;
        }

        System.out.print("請輸入課程容量: ");
        int capacity;
        try {
            capacity = Integer.parseInt(scanner.nextLine().trim());
            if (capacity <= 0) {
                System.out.println("失敗：課程容量必須大於 0！");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("失敗：容量必須為有效整數！");
            return;
        }

        courses[courseCount] = new Course(code, name, capacity);
        courseCount++;
        System.out.println("成功：已新增課程 [" + name + "]！");
    }

    public static void enrollCourse() {
        System.out.print("請輸入要選課的課程代碼: ");
        String code = scanner.nextLine().trim();
        int index = findCourseIndexByCode(code);

        if (index == -1) {
            System.out.println("失敗：找不到代碼為 [" + code + "] 的課程。");
            return;
        }

        if (courses[index].enroll()) {
            System.out.println("成功：選課成功！目前人數: " + courses[index].getCurrentStudents() + "/" + courses[index].getCapacity());
        } else {
            System.out.println("失敗：該課程已額滿，無法再加選！");
        }
    }

    public static void dropCourse() {
        System.out.print("請輸入要退選的課程代碼: ");
        String code = scanner.nextLine().trim();
        int index = findCourseIndexByCode(code);

        if (index == -1) {
            System.out.println("失敗：找不到代碼為 [" + code + "] 的課程。");
            return;
        }

        if (courses[index].drop()) {
            System.out.println("成功：退選成功！目前人數: " + courses[index].getCurrentStudents() + "/" + courses[index].getCapacity());
        } else {
            System.out.println("失敗：目前選課人數為 0，無法再退選！");
        }
    }

    public static void deleteCourse() {
        System.out.print("請輸入要刪除的課程代碼: ");
        String code = scanner.nextLine().trim();
        int index = findCourseIndexByCode(code);

        if (index == -1) {
            System.out.println("失敗：找不到代碼為 [" + code + "] 的課程。");
            return;
        }

        String targetName = courses[index].getName();
        for (int i = index; i < courseCount - 1; i++) {
            courses[i] = courses[i + 1];
        }
        courses[courseCount - 1] = null;
        courseCount--;

        System.out.println("成功：已刪除課程 [" + targetName + "]");
    }

    public static void searchCourse() {
        System.out.print("請輸入要搜尋的代碼或名稱關鍵字: ");
        String keyword = scanner.nextLine().trim();
        if (keyword.isEmpty()) {
            System.out.println("提示：搜尋關鍵字不可為空白！");
            return;
        }

        boolean found = false;
        for (int i = 0; i < courseCount; i++) {
            if (courses[i].getCode().equalsIgnoreCase(keyword)
                    || courses[i].getName().contains(keyword)) {
                System.out.println(courses[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("查無符合 [" + keyword + "] 的課程。");
        }
    }

    public static void listAllCourses() {
        if (courseCount == 0) {
            System.out.println("目前系統內沒有任何課程。");
            return;
        }
        System.out.println("--- 所有課程清單 (共 " + courseCount + " 門) ---");
        for (int i = 0; i < courseCount; i++) {
            System.out.println((i + 1) + ". " + courses[i]);
        }
    }

    public static void showStatistics() {
        int totalEnrollments = 0;
        int fullCount = 0;

        System.out.println("=== 系統統計報表 ===");
        System.out.println("總課程數：" + courseCount + " 門");

        for (int i = 0; i < courseCount; i++) {
            totalEnrollments += courses[i].getCurrentStudents();
            if (courses[i].isFull()) {
                fullCount++;
            }
        }

        System.out.println("總選課人次：" + totalEnrollments + " 人次");
        System.out.println("額滿課程數：" + fullCount + " 門");

        if (fullCount > 0) {
            System.out.println("--- 額滿課程清單 ---");
            for (int i = 0; i < courseCount; i++) {
                if (courses[i].isFull()) {
                    System.out.println("* " + courses[i]);
                }
            }
        } else {
            System.out.println("目前沒有任何額滿課程。");
        }
    }

    private static int findCourseIndexByCode(String code) {
        if (code == null || code.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < courseCount; i++) {
            if (courses[i].getCode().equalsIgnoreCase(code)) {
                return i;
            }
        }
        return -1;
    }
}