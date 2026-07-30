import java.util.Arrays;
import java.util.Comparator;
class Employee {
    private String id;
    private String name;
    private String department;
    private String extension;
    public Employee(String id, String name, String department, String extension) {
        this.id = (id == null) ? "" : id.trim();
        this.name = (name == null) ? "" : name.trim();
        this.department = (department == null) ? "" : department.trim();
        this.extension = (extension == null) ? "" : extension.trim();
    }
    public String getId() {
        return id;
    }
    @Override
    public String toString() {
        return String.format("員工編號: %-6s | 姓名: %-8s | 部門: %-10s | 分機: %s", 
                             id, name, department, extension);
    }
}
public class EmployeeSearchSystem {
    private Employee[] employees;
    public EmployeeSearchSystem(Employee[] inputEmployees) {
        if (inputEmployees == null) {
            this.employees = new Employee[0];
            return;
        }
        this.employees = Arrays.stream(inputEmployees)
                .filter(e -> e != null && e.getId() != null && !e.getId().trim().isEmpty())
                .toArray(Employee[]::new);
        Arrays.sort(this.employees, Comparator.comparing(Employee::getId, String.CASE_INSENSITIVE_ORDER));
    }
    public Employee searchById(String targetId) {
        if (targetId == null || targetId.trim().isEmpty()) {
            System.out.println("[查詢失敗] 輸入的查詢編號不可為空！");
            return null;
        }
        String searchKey = targetId.trim();
        int low = 0;
        int high = employees.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = employees[mid].getId().compareToIgnoreCase(searchKey);
            if (cmp == 0) {
                return employees[mid];
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println("[查詢失敗] 找不到編號為 " + searchKey + " 的員工。");
        return null;
    }
    public static void main(String[] args) {
        System.out.println("=== 課後作業三：員工編號查詢系統 ===");
        Employee[] data = {
            new Employee("E003", "張小明", "資訊部", "1234"),
            new Employee("E001", "李大華", "人事部", "5678"),
            new Employee("E005", "王美麗", "財務部", "9012"),
            new Employee("E002", "陳阿強", "業務部", "3456"),
            new Employee("  ", "無效資料", "無", "0000")
        };
        EmployeeSearchSystem system = new EmployeeSearchSystem(data);
        System.out.println("\n-- 查詢 E002 --");
        Employee emp = system.searchById("E002");
        if (emp != null) System.out.println("找到資料：\n" + emp);
        System.out.println("\n-- 查詢 e005 (小寫) --");
        emp = system.searchById("e005");
        if (emp != null) System.out.println("找到資料：\n" + emp);
        System.out.println("\n-- 查詢 E999 --");
        system.searchById("E999");
        System.out.println("\n-- 查詢空白 --");
        system.searchById("   ");
    }
}