package delivery.glovo.service;

import delivery.glovo.dto.OrderDto;
import delivery.glovo.mappers.OrderMapper;
import delivery.glovo.model.Order;
import delivery.glovo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDto> getOrders() {
        Iterable<Order> orders = orderRepository.findAll();
        return orderMapper.toOrderDtoList(orders);
    }

    @Override
    public OrderDto getOrderById(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow();
        return orderMapper.orderToOrderDto(order);
    }


    @Override
    public void saveNewOrder(OrderDto newOrder) {
        Order order = orderMapper.orderDtoToOrder(newOrder);
        orderRepository.save(order);
    }

    @Override
    public void updateOrderById(Integer id, OrderDto updatedOrder) {
        Order old = orderRepository.findById(id).orElseThrow();
        Order updated = orderMapper.updateToOrder(old, updatedOrder);
        orderRepository.save(updated);
    }

    @Override
    public void deleteOrderById(Integer id) {
        orderRepository.deleteById(id);
    }

}
