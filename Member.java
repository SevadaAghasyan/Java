public class Member {
    String id;
    String name;
    int status;
    String activity;
    Member left, right;

    public Member(String id, String name, int status, String activity) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.activity = activity;
        left = right = null;
    }

    public int getStatus() {
        return status;
    }

    public String toString() {
        return "ID: " + this.id + ", Name: " + this.name + ", Status: " + this.status
                + ", Activity: " + this.activity;
    }
}