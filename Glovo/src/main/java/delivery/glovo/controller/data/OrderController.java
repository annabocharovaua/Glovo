package delivery.glovo.controller.data;

import delivery.glovo.controller.response.ApiResponse;
import delivery.glovo.dto.OrderDto;
import delivery.glovo.service.data.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public ApiResponse<OrderDto> getById(@PathVariable("id") int id) {
        ApiResponse<OrderDto> apiResponse = new ApiResponse<>();
        OrderDto orderDto = orderService.getOrderById(id);
        if (orderDto != null) {
            apiResponse.setSuccess(true);
            apiResponse.setData(orderDto);
            apiResponse.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }
        return apiResponse;
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
    public ApiResponse<OrderDto> save(@RequestBody OrderDto orderDto) {
        ApiResponse<OrderDto> apiResponse = new ApiResponse<>();
        orderService.saveNewOrder(orderDto);
        OrderDto newOrder = orderService.getOrderById(orderDto.getId());
        if (newOrder != null && newOrder.getDate().equals(orderDto.getDate())
                && Objects.equals(newOrder.getCost(), orderDto.getCost())) {
            apiResponse.setSuccess(true);
            apiResponse.setData(newOrder);
            apiResponse.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<OrderDto> updateById(@PathVariable("id") int id, @RequestBody OrderDto orderDto) {
        ApiResponse<OrderDto> apiResponse = new ApiResponse<>();
        orderService.updateOrderById(id, orderDto);
        OrderDto updatedOrder = orderService.getOrderById(id);
        if (updatedOrder != null && updatedOrder.getDate().equals(orderDto.getDate())
                && Objects.equals(updatedOrder.getCost(), orderDto.getCost())) {
            apiResponse.setSuccess(true);
            apiResponse.setData(updatedOrder);
            apiResponse.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }
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
