package model;

import java.time.Instant;

public class Product {
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;

    // hàm khởi tạo
    public Product(){

    }
    public Product(long id, String name, double price, int quantity, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }
    public Product(Long id, String name, Double price,Integer quantity, String description,Instant createdAt,Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt =updatedAt;
    }
    //hàm tạo các trường của sản phẩm
    public static Product parse(String record){
        String [] fileds = record.split(",");
        long id = Long.parseLong(fileds[0]);
        String name = fileds[1];
        double price = Double.parseDouble(fileds[2]);
        int quantity = Integer.parseInt(fileds[3]);
        String description = fileds[4];
        Instant createAt = Instant.parse(fileds[5]);
        String temp = fileds[6];
        Instant updateAt = null;
        if (temp != null && !temp.equals("null"))
            updateAt = Instant.parse(temp);
        return new Product(id,name,price,quantity,description,createAt,updateAt);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setUpdateAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,",
                id,
                name,
                price,
                quantity,
                description,
                createdAt,
                updatedAt);
    }

}

