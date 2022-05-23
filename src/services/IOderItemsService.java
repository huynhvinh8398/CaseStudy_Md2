package services;

import model.OrderItem;

import java.util.List;

public interface IOderItemsService {
    List<OrderItem> findAll();
    void add(OrderItem newOrderItem);
    void update(OrderItem newOrderItem);
    OrderItem getOrderItemById(int id);
}
