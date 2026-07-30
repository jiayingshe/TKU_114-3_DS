import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class EventRegistrationSystem {
    private final int capacity;
    private List<Registration> masterList = new ArrayList<>();
    private Queue<Registration> waitingQueue = new LinkedList<>();
    private Stack<Registration> cancelStack = new Stack<>();
    private int activeCount = 0;

    public EventRegistrationSystem(int capacity) {
        this.capacity = capacity;
    }

    public boolean register(int id, String name) {
        for (Registration r : masterList) {
            if (r.getId() == id) {
                System.out.println("報名失敗：編號 " + id + " 已存在！");
                return false;
            }
        }

        Registration reg;
        if (activeCount < capacity) {
            reg = new Registration(id, name, "正式成功");
            activeCount++;
        } else {
            reg = new Registration(id, name, "候補中");
            waitingQueue.offer(reg);
        }
        masterList.add(reg);
        return true;
    }

    public boolean cancelRegistration(int id) {
        Registration target = null;
        for (Registration r : masterList) {
            if (r.getId() == id && !r.getStatus().equals("已取消")) {
                target = r;
                break;
            }
        }

        if (target == null) {
            System.out.println("取消失敗：找不到編號 " + id + " 的有效報名！");
            return false;
        }

        if (target.getStatus().equals("正式成功")) {
            target.setStatus("已取消");
            cancelStack.push(target);
            activeCount--;

            if (!waitingQueue.isEmpty()) {
                Registration candidate = waitingQueue.poll();
                candidate.setStatus("正式成功");
                activeCount++;
            }
        } else if (target.getStatus().equals("候補中")) {
            target.setStatus("已取消");
            waitingQueue.remove(target);
            cancelStack.push(target);
        }
        return true;
    }

    public List<Registration> undoCancellations(int count) {
        List<Registration> restored = new ArrayList<>();
        while (count > 0 && !cancelStack.isEmpty()) {
            Registration reg = cancelStack.pop();
            if (activeCount < capacity) {
                reg.setStatus("正式成功");
                activeCount++;
            } else {
                reg.setStatus("候補中");
                waitingQueue.offer(reg);
            }
            restored.add(reg);
            count--;
        }
        return restored;
    }

    public void sortMasterListById() {
        if (!masterList.isEmpty()) {
            RegistrationAlgorithms.mergeSortByIdAsc(masterList, 0, masterList.size() - 1);
        }
    }

    public Registration findById(int id) {
        sortMasterListById();
        return RegistrationAlgorithms.binarySearchById(masterList, id);
    }

    public Registration findByName(String name) {
        return RegistrationAlgorithms.sequentialSearchByName(masterList, name);
    }

    public void displayAll() {
        System.out.println("--------------------------------------------");
        for (Registration r : masterList) {
            System.out.println(r);
        }
        System.out.println("--------------------------------------------");
    }

    public static void main(String[] args) {
        EventRegistrationSystem sys = new EventRegistrationSystem(3);

        System.out.println("=== 測試 1：報名與額滿候補 ===");
        sys.register(103, "張三");
        sys.register(101, "李四");
        sys.register(105, "王五");
        sys.register(102, "趙六");
        sys.register(104, "錢七");
        sys.displayAll();

        System.out.println("\n=== 測試 2：重複編號註冊 ===");
        sys.register(101, "重複李四");

        System.out.println("\n=== 測試 3：取消正式名額與候補遞補 ===");
        sys.cancelRegistration(103);
        sys.displayAll();

        System.out.println("\n=== 測試 4：取消不存在的編號 ===");
        sys.cancelRegistration(999);

        System.out.println("\n=== 測試 5：復原取消記錄 (Undo 1 筆) ===");
        sys.undoCancellations(1);
        sys.displayAll();

        System.out.println("\n=== 測試 6：二分查找與順序查找 ===");
        System.out.println("二分查找編號 102: " + sys.findById(102));
        System.out.println("順序查找姓名 '錢七': " + sys.findByName("錢七"));
    }
}