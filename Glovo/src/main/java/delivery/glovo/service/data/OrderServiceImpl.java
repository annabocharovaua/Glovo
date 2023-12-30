package delivery.glovo.service.data;

import delivery.glovo.converter.OrderConverter;
import delivery.glovo.dto.OrderDto;
import delivery.glovo.model.data.Order;
import delivery.glovo.repository.data.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    @Override
    public List<OrderDto> getOrders() {
        Iterable<Order> orders = orderRepository.findAll();
        return orderConverter.fromModel(orders);
    }

    @Override
    public OrderDto getOrderById(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow();
        return orderConverter.fromModel(order);
    }


    @Override
    public void saveNewOrder(OrderDto newOrder) {
        Order order = orderConverter.toModel(newOrder);
        orderRepository.save(order);
    }

    @Override
    public void updateOrderById(Integer id, OrderDto updatedOrder) {
        Order old = orderRepository.findById(id).orElseThrow();
        Order updated = orderConverter.toModel(old, updatedOrder);
        orderRepository.save(updated);
    }

    @Override
    public void deleteOrderById(Integer id) {

        orderRepository.deleteById(id);
    }

}
