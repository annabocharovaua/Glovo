package delivery.glovo.service;

import delivery.glovo.dto.OrderDto;
import delivery.glovo.repository.jdbc.OrdersJDBCRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private List<OrderDto> orders;
    private final OrdersJDBCRepository ordersJDBCRepository;

    @Override
    public OrderDto getOrderById(int id) {
        return ordersJDBCRepository.getById(id);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return ordersJDBCRepository.getAll();
    }

    @Override
    public void addOrder(OrderDto order) {
        ordersJDBCRepository.save(order);
    }

    @Override
    public void updateOrderById(int id, OrderDto updatedOrder) {
        ordersJDBCRepository.updateById(id, updatedOrder);
    }

    @Override
    public void deleteOrderById(int id) {
        ordersJDBCRepository.deleteById(id);
    }
}
