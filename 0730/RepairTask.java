public class RepairTask {
    private int id;
    private String deviceName;
    private int priority;
    private String status;

    public RepairTask(int id, String deviceName, int priority) {
        this.id = id;
        this.deviceName = deviceName;
        this.priority = priority;
        this.status = "等待中";
    }

    public int getId() { return id; }
    public String getDeviceName() { return deviceName; }
    public int getPriority() { return priority; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("任務ID: %-4d | 設備名稱: %-12s | 優先級: %d | 狀態: %s", id, deviceName, priority, status);
    }
}