public class TaskNode {
    String id;
    String description;
    boolean completed;
    TaskNode next;
    public TaskNode(String id, String description) {
        this.id = (id != null) ? id.trim() : "";
        this.description = (description != null) ? description.trim() : "";
        this.completed = false;
        this.next = null;
    }
}