package delivery.glovo.service;

import delivery.glovo.dto.OrderDto;

import java.util.List;

public interface OrderService {
    public OrderDto getOrderById(int id);

    public List<OrderDto> getAllOrders();

    public void addOrder(OrderDto order);

    public void updateOrderById(int id, OrderDto updatedOrder);

    public void deleteOrderById(int id);
}
