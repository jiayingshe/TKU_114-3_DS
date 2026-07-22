public class PlaylistLinkedList {
    private PlaylistNode head;
    private int size;
    public PlaylistLinkedList() {
        this.head = null;
        this.size = 0;
    }
    public PlaylistNode findById(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }
        PlaylistNode curr = head;
        while (curr != null) {
            if (curr.id.equalsIgnoreCase(id.trim())) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }
    public boolean addLast(String id, String title) {
        if (id == null || id.trim().isEmpty() || title == null || title.trim().isEmpty()) {
            return false;
        }
        if (findById(id) != null) {
            return false;
        }
        PlaylistNode newNode = new PlaylistNode(id, title);
        if (head == null) {
            head = newNode;
        } else {
            PlaylistNode curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        size++;
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
        PlaylistNode curr = head;
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
    public void printPlaylist() {
        if (head == null) {
            System.out.println("播放清單為空。");
            return;
        }
        System.out.println("--- 播放清單 (共 " + size + " 首) ---");
        PlaylistNode curr = head;
        int index = 1;
        while (curr != null) {
            System.out.println(index + ". [" + curr.id + "] " + curr.title);
            curr = curr.next;
            index++;
        }
    }
    public int getSize() {
        return size;
    }
}