public class Course {
    private String code;
    private String name;
    private int capacity;
    private int currentStudents;

    public Course(String code, String name, int capacity) {
        this.code = (code != null) ? code.trim() : "";
        this.name = (name != null) ? name.trim() : "";
        this.capacity = Math.max(capacity, 1);
        this.currentStudents = 0;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentStudents() {
        return currentStudents;
    }

    public boolean isFull() {
        return currentStudents >= capacity;
    }

    public boolean enroll() {
        if (isFull()) {
            return false;
        }
        currentStudents++;
        return true;
    }

    public boolean drop() {
        if (currentStudents <= 0) {
            return false;
        }
        currentStudents--;
        return true;
    }

    @Override
    public String toString() {
        String status = isFull() ? " [已額滿]" : "";
        return "代碼: " + code + " | 課程: " + name + " | 人數: " + currentStudents + "/" + capacity + status;
    }
}