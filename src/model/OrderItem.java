package model;

public class OrderItem {
    private Long id;
    private double price;
    private int quantity;
    private long orderId;
    private int productId;
    private String productName;
    // hàm khởi tạo
    public OrderItem(long id, double price, int quantity, long orderId, int productId, String productName, double total) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
    }

    public OrderItem() {

    }
    // hàm tạo ra các trường dữ liệu của sản phẩm
    public OrderItem(String record) {
        String[] fields = record.split(",");
        id = Long.parseLong(fields[0]);
        price = Double.parseDouble(fields[1]);
        quantity = Integer.parseInt(fields[2]);
        orderId = Long.parseLong(fields[3]);
        productId = Integer.parseInt(fields[4]);
        productName = fields[5];
    }
  // các phương thức get ,set
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return id + "," + price + "," + quantity + "," + orderId + "," + productId + "," + productName;
    }


}
