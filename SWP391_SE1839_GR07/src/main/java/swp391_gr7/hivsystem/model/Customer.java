package swp391_gr7.hivsystem.model;
import jakarta.persistence.*;

@Entity
@Table(name = "Customer") // đổi tên bảng thành "users" tránh trùng từ khóa reserved
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true) // bỏ length với int
    private int id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 100) // tăng độ dài cho password (ví dụ 100)
    private String password;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    public Customer() {
    }

    public Customer(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getter và Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
