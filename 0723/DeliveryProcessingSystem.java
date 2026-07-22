import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DeliveryProcessingSystem {
    private Queue<DeliveryTask> pendingQueue;
    private Stack<DeliveryTask> completedStack;
    public DeliveryProcessingSystem() {
        this.pendingQueue = new LinkedList<>();
        this.completedStack = new Stack<>();
    }
    public boolean addTask(String id, String address) {
        if (id == null || id.trim().isEmpty() || address == null || address.trim().isEmpty()) {
            System.out.println("新增失敗：代碼與地址不可為空白。");
            return false;
        }
        for (DeliveryTask task : pendingQueue) {
            if (task.getId().equalsIgnoreCase(id.trim())) {
                System.out.println("新增失敗：任務代碼 " + id.trim() + " 已存在於等待佇列！");
                return false;
            }
        }
        for (DeliveryTask task : completedStack) {
            if (task.getId().equalsIgnoreCase(id.trim())) {
                System.out.println("新增失敗：任務代碼 " + id.trim() + " 已存在於完成紀錄！");
                return false;
            }
        }
        DeliveryTask task = new DeliveryTask(id, address);
        pendingQueue.offer(task);
        System.out.println("成功新增任務：" + task);
        return true;
    }
    public DeliveryTask processNext() {
        if (pendingQueue.isEmpty()) {
            System.out.println("處理失敗：目前沒有待配送工作。");
            return null;
        }
        DeliveryTask task = pendingQueue.poll();
        completedStack.push(task);
        System.out.println("完成配送：" + task);
        return task;
    }
    public DeliveryTask peekNext() {
        if (pendingQueue.isEmpty()) {
            System.out.println("查看提示：目前沒有下一筆待配送工作。");
            return null;
        }
        DeliveryTask task = pendingQueue.peek();
        System.out.println("下一筆待配送：" + task);
        return task;
    }
    public boolean undoLastCompleted() {
        if (completedStack.isEmpty()) {
            System.out.println("復原失敗：目前沒有已完成的紀錄可供復原。");
            return false;
        }
        DeliveryTask task = completedStack.pop();
        pendingQueue.offer(task);
        System.out.println("已復原任務並送回等待尾端：" + task);
        return true;
    }
    public void printStatus() {
        System.out.println("=== 系統狀態統計 ===");
        System.out.println("等待配送數量：" + pendingQueue.size());
        System.out.println("已完成數量：" + completedStack.size());
        System.out.println("\n[待配送 Queue 順序]");
        if (pendingQueue.isEmpty()) {
            System.out.println(" (無)");
        } else {
            int rank = 1;
            for (DeliveryTask task : pendingQueue) {
                System.out.println(" " + rank + ". " + task);
                rank++;
            }
        }
        System.out.println("\n[完成紀錄 Stack (最新在最上)]");
        if (completedStack.isEmpty()) {
            System.out.println(" (無)");
        } else {
            for (int i = completedStack.size() - 1; i >= 0; i--) {
                System.out.println(" " + (completedStack.size() - i) + ". " + completedStack.get(i));
            }
        }
        System.out.println();
    }
    public static void main(String[] args) {
        DeliveryProcessingSystem system = new DeliveryProcessingSystem();
        System.out.println("=== 測試 1: 空結構安全操作 ===");
        system.processNext();
        system.peekNext();
        system.undoLastCompleted();
        system.printStatus();
        System.out.println("=== 測試 2: 新增配送任務 ===");
        system.addTask("D01", "台北市信義區忠孝東路五段");
        system.addTask("D02", "新北市板橋區縣民大道二段");
        system.addTask("D03", "台中市西屯區台灣大道三段");
        system.addTask("D01", "重複代碼測試");
        system.printStatus();
        System.out.println("=== 測試 3: 查看與完成任務 ===");
        system.peekNext();
        system.processNext();
        system.processNext();
        system.printStatus();
        System.out.println("=== 測試 4: 復原最近完成並回到等待尾端 ===");
        system.undoLastCompleted();
        system.printStatus();
        System.out.println("=== 測試 5: 繼續處理與清空佇列 ===");
        system.processNext();
        system.processNext();
        system.printStatus();
    }
}