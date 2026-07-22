public class NumberHistoryList {
    private static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    private Node head;
    private int size;
    public NumberHistoryList() {
        this.head = null;
        this.size = 0;
    }
    public void addFirst(int val) {
        Node newNode = new Node(val);
        newNode.next = head;
        head = newNode;
        size++;
    }
    public void addLast(int val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        size++;
    }
    public boolean search(int val) {
        Node curr = head;
        while (curr != null) {
            if (curr.data == val) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }
    public boolean remove(int val) {
        if (head == null) {
            return false;
        }
        if (head.data == val) {
            head = head.next;
            size--;
            return true;
        }
        Node curr = head;
        while (curr.next != null) {
            if (curr.next.data == val) {
                curr.next = curr.next.next;
                size--;
                return true;
            }
            curr = curr.next;
        }
        return false;
    }
    public void printList() {
        if (head == null) {
            System.out.println("串列內容：[ 空串列 ]");
            return;
        }
        System.out.print("串列內容：");
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + (curr.next != null ? " -> " : ""));
            curr = curr.next;
        }
        System.out.println();
    }
    public void printStats() {
        if (head == null) {
            System.out.println("統計結果：size=0 | 總和=0 | 最大值=N/A | 最小值=N/A");
            return;
        }
        int sum = 0;
        int max = head.data;
        int min = head.data;
        Node curr = head;
        while (curr != null) {
            sum += curr.data;
            if (curr.data > max) max = curr.data;
            if (curr.data < min) min = curr.data;
            curr = curr.next;
        }
        System.out.println("統計結果：size=" + size + " | 總和=" + sum + " | 最大值=" + max + " | 最小值=" + min);
    }
    public static void main(String[] args) {
        NumberHistoryList list = new NumberHistoryList();
        System.out.println("--- 操作 1: 印出空串列統計 ---");
        list.printStats();
        System.out.println("--- 操作 2: 前端新增 20 ---");
        list.addFirst(20);
        System.out.println("--- 操作 3: 前端新增 10 ---");
        list.addFirst(10);
        System.out.println("--- 操作 4: 尾端新增 30 ---");
        list.addLast(30);
        System.out.println("--- 操作 5: 尾端新增 40 ---");
        list.addLast(40);
        list.printList();
        list.printStats();
        System.out.println("--- 操作 6: 搜尋測試 ---");
        System.out.println("搜尋 30：" + list.search(30));
        System.out.println("搜尋 99：" + list.search(99));
        System.out.println("--- 操作 7: 刪除 10（頭部） ---");
        list.remove(10);
        list.printList();
        System.out.println("--- 操作 8: 刪除 30（中間）與 99（不存在） ---");
        list.remove(30);
        System.out.println("刪除 99 結果：" + list.remove(99));
        list.printList();
        list.printStats();
    }
}