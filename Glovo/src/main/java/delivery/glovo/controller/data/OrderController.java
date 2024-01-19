package delivery.glovo.controller.data;

import delivery.glovo.controller.response.ApiResponse;
import delivery.glovo.dto.OrderDto;
import delivery.glovo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") Integer id) {
        OrderDto order = orderService.getOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        }
        return (ResponseEntity<OrderDto>) ResponseEntity.notFound();
    }

    @GetMapping()
    public ApiResponse<List<OrderDto>> getAll() {
        ApiResponse<List<OrderDto>> apiResponse = new ApiResponse<>();
        List<OrderDto> orderDtoList = orderService.getOrders();
        if (!CollectionUtils.isEmpty(orderDtoList)) {
            apiResponse.setSuccess(true);
            apiResponse.setData(orderDtoList);
            apiResponse.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }

        return apiResponse;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody OrderDto orderDto) {
        orderService.saveNewOrder(orderDto);
    }

    @PutMapping("/{id}")
    public ApiResponse<OrderDto> updateById(@PathVariable("id") int id, @RequestBody OrderDto orderDto) {
        ApiResponse<OrderDto> apiResponse = new ApiResponse<>();
        orderService.updateOrderById(id, orderDto);
        OrderDto updatedOrder = orderService.getOrderById(id);

        apiResponse.setSuccess(true);
        apiResponse.setData(updatedOrder);
        apiResponse.setMessages(Stream.of(HttpStatus.OK.toString()).toList());

        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<OrderDto> deleteById(@PathVariable("id") int id) {
        ApiResponse<OrderDto> apiResponse = new ApiResponse<>();
        orderService.deleteOrderById(id);
        try {
            orderService.getOrderById(id);
        } catch (NoSuchElementException e) {
            apiResponse.setSuccess(true);
            apiResponse.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }
        return apiResponse;
    }



}
