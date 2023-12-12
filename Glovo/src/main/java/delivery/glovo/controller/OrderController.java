package delivery.glovo.controller;

import delivery.glovo.dto.OrderDto;
import delivery.glovo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/orders/{id}")
    public OrderDto getById(@PathVariable("id") int id) {
        try {
            return orderService.getOrderById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/orders")
    public List<OrderDto> getOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/save/order")
    public void save(@RequestBody OrderDto orderDto) {
        orderService.addOrder(orderDto);
    }

    @PutMapping("/update/order/{id}")
    public void updateById(@PathVariable("id") int id, @RequestBody OrderDto orderDto) {
        orderService.updateOrderById(id, orderDto);
    }

    @DeleteMapping("/delete/order/{id}")
    public void deleteById(@PathVariable("id") int id) {
        orderService.deleteOrderById(id);
    }

}
