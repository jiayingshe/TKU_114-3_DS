public class Patient {
    private int id;
    private String name;
    private String department;
    public Patient(int id, String name, String department) {
        this.id = id;
        this.name = (name != null) ? name.trim() : "";
        this.department = (department != null) ? department.trim() : "";
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDepartment() {
        return department;
    }
    @Override
    public String toString() {
        return "號碼: " + id + " | 姓名: " + name + " | 科別: " + department;
    }
}