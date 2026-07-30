import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class RepairSchedulingSystem {
    private List<RepairTask> allTasks = new ArrayList<>();
    private Queue<RepairTask> waitingQueue = new LinkedList<>();
    private Stack<RepairTask> completedStack = new Stack<>();

    public void addTask(RepairTask task) {
        allTasks.add(task);
        waitingQueue.offer(task);
    }

    public void sortWaitingQueueByPriority() {
        if (waitingQueue.isEmpty()) return;
        List<RepairTask> list = new ArrayList<>(waitingQueue);
        RepairAlgorithms.mergeSortByPriorityDesc(list, 0, list.size() - 1);
        waitingQueue.clear();
        waitingQueue.addAll(list);
    }

    public RepairTask completeNextTask() {
        if (waitingQueue.isEmpty()) return null;
        RepairTask task = waitingQueue.poll();
        task.setStatus("已完成");
        completedStack.push(task);
        return task;
    }

    public List<RepairTask> undoCompletedTasks(int count) {
        List<RepairTask> restored = new ArrayList<>();
        while (count > 0 && !completedStack.isEmpty()) {
            RepairTask task = completedStack.pop();
            task.setStatus("等待中");
            waitingQueue.offer(task);
            restored.add(task);
            count--;
        }
        return restored;
    }

    public void displayStatistics() {
        System.out.println("================ 系統工作統計 ================");
        System.out.println("總工作數: " + allTasks.size());
        System.out.println("等待中工作數: " + waitingQueue.size());
        System.out.println("已完成工作數: " + completedStack.size());
        System.out.println("--------------------------------------------");
    }

    public static void main(String[] args) {
        RepairSchedulingSystem sys = new RepairSchedulingSystem();

        sys.addTask(new RepairTask(1, "伺服器A", 3));
        sys.addTask(new RepairTask(2, "印表機B", 1));
        sys.addTask(new RepairTask(3, "路由器C", 5));
        sys.addTask(new RepairTask(4, "交換器D", 5));
        sys.addTask(new RepairTask(5, "防火牆E", 2));

        System.out.println("=== 原始佇列排序前處理 ===");
        sys.displayStatistics();

        System.out.println("=== 依優先級排序等待佇列 (Merge Sort 降冪) ===");
        sys.sortWaitingQueueByPriority();

        System.out.println("\n=== 依序執行完成 3 項工作 ===");
        System.out.println("完成: " + sys.completeNextTask());
        System.out.println("完成: " + sys.completeNextTask());
        System.out.println("完成: " + sys.completeNextTask());

        sys.displayStatistics();

        System.out.println("\n=== 支援複數復原 (Undo 2 項工作) ===");
        List<RepairTask> undone = sys.undoCompletedTasks(2);
        for (RepairTask t : undone) {
            System.out.println("已復原至等待佇列: " + t);
        }

        sys.displayStatistics();

        System.out.println("\n=== 依設備名稱搜尋 ('伺服器A') ===");
        List<RepairTask> searchResult = RepairAlgorithms.sequentialSearchByDevice(sys.allTasks, "伺服器A");
        for (RepairTask t : searchResult) {
            System.out.println(t);
        }
    }
}