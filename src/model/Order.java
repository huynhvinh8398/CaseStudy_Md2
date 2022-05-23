package model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private String fullname;
    private String phone;
    private String address;
    private Double total;
    private Instant crearedAt;
//khởi tạo 1 List order
 List<OrderItem> orderItems = new ArrayList<>();
// Hàm khởi tạo
    private Order(){

    }

    public Order (long id, String fullname, String phone, String address) {
        this.id = id;
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
    }
    //Hàm khởi tạo các trường dữ liệu của người dùng order
    public static Order parse (String record) {
        Order order = new Order();
        String [] field = record.split(",");
        order.id = Long.parseLong(field[0]);
        order.fullname = field[1];
        order.phone = field[2];
        order.address = field[3];
        return order;
    }
    //Các phương thức get và set
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullname;
    }

    public void setFullName(String fullName) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double geTotal() {
        return total;
    }

    public void setTotal(Double Total) {
        this.total = total;
    }

    public Instant getCrearedAt() {
        return crearedAt;
    }

    public void setCrearedAt(Instant crearedAt) {
        this.crearedAt = crearedAt;
    }

    @Override
    public String toString() {
        return id + "," + fullname + "," + phone + "," + address;
    }

}

