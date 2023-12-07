package delivery.glovo.service;

import delivery.glovo.dto.Order;

import java.util.List;

public interface OrderService {
    public Order getOrderById(int id);
    public List<Order> getAllOrders();
    public void addOrder(Order order);
    public void updateOrderById(int id, Order updatedOrder);
    public void deleteOrderById(int id);


}
