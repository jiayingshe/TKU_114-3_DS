public class DeliveryTask {
    private String id;
    private String address;
    public DeliveryTask(String id, String address) {
        this.id = (id != null) ? id.trim() : "";
        this.address = (address != null) ? address.trim() : "";
    }
    public String getId() {
        return id;
    }
    public String getAddress() {
        return address;
    }
    @Override
    public String toString() {
        return "任務代碼: " + id + " | 配送地址: " + address;
    }
}