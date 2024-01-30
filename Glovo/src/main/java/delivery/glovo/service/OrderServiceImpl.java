package delivery.glovo.service;

import delivery.glovo.dto.OrderDto;
import delivery.glovo.mappers.OrderMapper;
import delivery.glovo.model.Order;
import delivery.glovo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDto> getOrders() {
        Iterable<Order> orders = orderRepository.findAll();
        log.debug("Get all orders : " + orders);
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
        log.debug("Order saved : " + order);
    }

    @Override
    public void updateOrderById(Integer id, OrderDto updatedOrder) {
        Order old = orderRepository.findById(id).orElseThrow();
        Order updated = orderMapper.updateToOrder(old, updatedOrder);
        Order saved = orderRepository.save(updated);
        log.debug("Order updated : " + saved);
    }

    @Override
    public void deleteOrderById(Integer id) {
        orderRepository.deleteById(id);
    }

}
