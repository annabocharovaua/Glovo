package delivery.glovo.controller.orders;

import delivery.glovo.controller.response.ApiResponse;
import delivery.glovo.dto.OrderDto;
import delivery.glovo.service.GlovoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {
    private final GlovoService glovoService;

    @GetMapping("/{id}")
    public ApiResponse<OrderDto> getById(@PathVariable("id") int id) {
        ApiResponse<OrderDto> apiResponse = new ApiResponse<>();
        OrderDto orderDto = glovoService.getOrder(id);
        if (orderDto != null) {
            apiResponse.setSuccess(true);
            apiResponse.setData(orderDto);
            apiResponse.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }
        return apiResponse;
    }

    @GetMapping()
    public ApiResponse<List<OrderDto>> getOrders() {
        ApiResponse<List<OrderDto>> apiResponse = new ApiResponse<>();
        List<OrderDto> orderDtoList = glovoService.getOrders();
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
        glovoService.addOrder(orderDto);
        OrderDto newOrder = glovoService.getOrder(orderDto.getId());
        if (newOrder != null && newOrder.getDate().equals(orderDto.getDate())
                && newOrder.getCost() == orderDto.getCost()) {
            apiResponse.setSuccess(true);
            apiResponse.setData(newOrder);
            apiResponse.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<OrderDto> updateById(@PathVariable("id") int id, @RequestBody OrderDto orderDto) {
        ApiResponse<OrderDto> apiResponse = new ApiResponse<>();
        glovoService.updateOrder(id, orderDto);
        OrderDto updatedOrder = glovoService.getOrder(id);
        if (updatedOrder != null && updatedOrder.getDate().equals(orderDto.getDate())
                && updatedOrder.getCost() == orderDto.getCost()) {
            apiResponse.setSuccess(true);
            apiResponse.setData(updatedOrder);
            apiResponse.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<OrderDto> deleteById(@PathVariable("id") int id) {
        ApiResponse<OrderDto> apiResponse = new ApiResponse<>();
        glovoService.deleteOrder(id);
        try {
            glovoService.getOrder(id);
        } catch (EmptyResultDataAccessException e) {
            apiResponse.setSuccess(true);
            apiResponse.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }
        return apiResponse;
    }

}
