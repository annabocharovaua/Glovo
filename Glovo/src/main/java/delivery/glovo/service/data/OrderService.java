package delivery.glovo.service.data;

import delivery.glovo.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getOrders();
    OrderDto getOrderById(Integer id);
    void saveNewOrder(OrderDto newOrder);
    void updateOrderById(Integer id, OrderDto updatedOrder);
    void deleteOrderById(Integer id);
}
