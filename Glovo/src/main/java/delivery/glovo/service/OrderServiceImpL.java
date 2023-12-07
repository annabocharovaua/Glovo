package delivery.glovo.service;

import delivery.glovo.dto.Order;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpL implements OrderService {

    private List<Order> orders;

    @Override
    public Order getOrderById(int id) {
        return orders.stream()
                .filter(order -> order.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Order> getAllOrders() {
        return orders;
    }

    @Override
    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public void updateOrderById(int id, Order updatedOrder) {
        orders.stream()
                .filter(order -> order.getId() == id)
                .findFirst()
                .ifPresentOrElse(order -> orders.set(orders.indexOf(order), updatedOrder),
                        () -> System.out.println("Замовлення за id " + id + " не знайдено"));

    }

    @Override
    public void deleteOrderById(int id) {
        orders.removeIf(order -> order.getId() == id);
    }
}
