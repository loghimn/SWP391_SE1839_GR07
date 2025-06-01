package swp391_gr7.hivsystem.message;

public class registerMessage {
    private String message;
    private boolean status;

    public registerMessage() {
    }

    public registerMessage(String message, boolean status) {
        this.message = message;
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}
