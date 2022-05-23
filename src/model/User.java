package model;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private String phone;
    private String email;
    private String address;
    private Role role;
    private Instant createdAt;
    private Instant updatedAt;

    List<Order> orders = new ArrayList<>();

    public User() {

    }
    public User(long id,String username, String password, String fullName,String phone,String email,String address ,Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.role = role;
    }
    public static User paraseUser(String raw) {
        User user = new User();
        String [] fileds = raw.split(",");
        user.id = Long.parseLong( fileds[0]);
        user.username = fileds[1];
        user.password = fileds[2];
        user.fullName = fileds [3];
        user.phone = fileds[4];
        user.email = fileds[5];
        user.address = fileds [6];
        user.role = Role.parseRole(fileds[7]);
        user.createdAt = Instant.parse(fileds[8]);
        String temp = fileds[9];
        if (temp != null && !temp.equals("null"))
            user.updatedAt = Instant.parse(temp);
         return user;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                id,
                username,
                password,
                fullName,
                phone,
                email,
                address,
                role,
                createdAt,
                updatedAt
        );
    }
}
