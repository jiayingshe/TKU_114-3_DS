public class TaskLinkedList {
    private TaskNode head;
    private int size;
    public TaskLinkedList() {
        this.head = null;
        this.size = 0;
    }
    public TaskNode findById(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }
        TaskNode curr = head;
        while (curr != null) {
            if (curr.id.equalsIgnoreCase(id.trim())) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }
    public boolean addFirst(String id, String description) {
        if (id == null || id.trim().isEmpty() || description == null || description.trim().isEmpty()) {
            return false;
        }
        if (findById(id) != null) {
            return false;
        }
        TaskNode newNode = new TaskNode(id, description);
        newNode.next = head;
        head = newNode;
        size++;
        return true;
    }
    public boolean addLast(String id, String description) {
        if (id == null || id.trim().isEmpty() || description == null || description.trim().isEmpty()) {
            return false;
        }
        if (findById(id) != null) {
            return false;
        }
        TaskNode newNode = new TaskNode(id, description);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        size++;
        return true;
    }
    public boolean completeTask(String id) {
        TaskNode task = findById(id);
        if (task == null) {
            return false;
        }
        task.completed = true;
        return true;
    }
    public boolean removeById(String id) {
        if (head == null || id == null || id.trim().isEmpty()) {
            return false;
        }
        String targetId = id.trim();
        if (head.id.equalsIgnoreCase(targetId)) {
            head = head.next;
            size--;
            return true;
        }
        TaskNode curr = head;
        while (curr.next != null) {
            if (curr.next.id.equalsIgnoreCase(targetId)) {
                curr.next = curr.next.next;
                size--;
                return true;
            }
            curr = curr.next;
        }
        return false;
    }
    public void printPendingTasks() {
        int pendingCount = getPendingCount();
        System.out.println("=== 未完成工作清單 (未完成: " + pendingCount + " / 總數: " + size + ") ===");
        if (pendingCount == 0) {
            System.out.println("目前沒有未完成的工作。");
            return;
        }
        TaskNode curr = head;
        int index = 1;
        while (curr != null) {
            if (!curr.completed) {
                System.out.println(index + ". [" + curr.id + "] " + curr.description);
                index++;
            }
            curr = curr.next;
        }
    }
    public int getPendingCount() {
        int count = 0;
        TaskNode curr = head;
        while (curr != null) {
            if (!curr.completed) {
                count++;
            }
            curr = curr.next;
        }
        return count;
    }
    public int getSize() {
        return size;
    }
}