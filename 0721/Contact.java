public class Contact {
    private String id;
    private String name;
    private String phone;
    private String email;

    public Contact(String id, String name, String phone, String email) {
        this.id = (id != null) ? id.trim() : "";
        this.name = (name != null) ? name.trim() : "";
        this.phone = (phone != null) ? phone.trim() : "";
        this.email = (email != null) ? email.trim() : "";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone != null && !phone.trim().isEmpty()) {
            this.phone = phone.trim();
        }
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "代碼: " + id + " | 姓名: " + name + " | 電話: " + phone + " | Email: " + email;
    }
}