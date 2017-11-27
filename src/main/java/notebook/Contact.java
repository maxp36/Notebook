package notebook;

public class Contact {
    private int id;
    private String phone;
    private String name;
    private String email;
    private String note;

    private final int NUM_COL = 5;

    public Contact(int id, String phone, String name, String email, String note) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getNumCol() {
        return NUM_COL;
    }
}
