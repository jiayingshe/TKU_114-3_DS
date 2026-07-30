public class Registration {
    private int id;
    private String name;
    private String status;

    public Registration(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("報名編號: %-4d | 姓名: %-8s | 狀態: %s", id, name, status);
    }
}