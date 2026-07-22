import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ClinicQueueSystem {
    private Queue<Patient> queue;
    private Set<Integer> existingIds;
    private int servedCount;
    public ClinicQueueSystem() {
        this.queue = new LinkedList<>();
        this.existingIds = new HashSet<>();
        this.servedCount = 0;
    }
    public boolean register(int id, String name, String department) {
        if (id <= 0 || name == null || name.trim().isEmpty() || department == null || department.trim().isEmpty()) {
            System.out.println("掛號失敗：輸入資料格式無效。");
            return false;
        }
        if (existingIds.contains(id)) {
            System.out.println("掛號失敗：號碼 " + id + " 已存在！");
            return false;
        }
        Patient p = new Patient(id, name, department);
        queue.offer(p);
        existingIds.add(id);
        System.out.println("掛號成功：" + p);
        return true;
    }
    public Patient callNext() {
        if (queue.isEmpty()) {
            System.out.println("叫號提示：目前沒有人在等待。");
            return null;
        }
        Patient p = queue.poll();
        servedCount++;
        System.out.println("請就診：" + p);
        return p;
    }
    public Patient peekNext() {
        if (queue.isEmpty()) {
            System.out.println("查看提示：目前沒有下一位患者。");
            return null;
        }
        Patient p = queue.peek();
        System.out.println("下一位患者：" + p);
        return p;
    }
    public void printWaitingList() {
        if (queue.isEmpty()) {
            System.out.println("等待清單：[ 空 ]");
            return;
        }
        System.out.println("=== 等待清單 (共 " + queue.size() + " 人) ===");
        int rank = 1;
        for (Patient p : queue) {
            System.out.println(rank + ". " + p);
            rank++;
        }
    }
    public void printStats() {
        Map<String, Integer> deptMap = new HashMap<>();
        for (Patient p : queue) {
            deptMap.put(p.getDepartment(), deptMap.getOrDefault(p.getDepartment(), 0) + 1);
        }
        System.out.println("=== 統計資訊 ===");
        System.out.println("已服務總人數：" + servedCount + " 人");
        System.out.println("目前等待總人數：" + queue.size() + " 人");
        if (deptMap.isEmpty()) {
            System.out.println("各科別等待人數：無");
        } else {
            System.out.println("各科別等待人數：");
            for (Map.Entry<String, Integer> entry : deptMap.entrySet()) {
                System.out.println(" - " + entry.getKey() + ": " + entry.getValue() + " 人");
            }
        }
    }
    public static void main(String[] args) {
        ClinicQueueSystem system = new ClinicQueueSystem();
        System.out.println("=== 測試 1: 空結構安全操作 ===");
        system.callNext();
        system.peekNext();
        system.printWaitingList();
        system.printStats();
        System.out.println("\n=== 測試 2: 掛號與重複號碼阻擋 ===");
        system.register(101, "張小明", "內科");
        system.register(102, "李大華", "外科");
        system.register(101, "王重複", "內科");
        system.register(103, "陳美麗", "內科");
        system.register(104, "林阿寶", "眼科");
        System.out.println("\n=== 測試 3: 查看下一位與印出等待清單 ===");
        system.peekNext();
        system.printWaitingList();
        system.printStats();
        System.out.println("\n=== 測試 4: 叫號與統計更新 ===");
        system.callNext();
        system.callNext();
        system.printWaitingList();
        system.printStats();
        System.out.println("\n=== 測試 5: 叫完所有號碼 ===");
        system.callNext();
        system.callNext();
        system.callNext();
        system.printStats();
    }
}