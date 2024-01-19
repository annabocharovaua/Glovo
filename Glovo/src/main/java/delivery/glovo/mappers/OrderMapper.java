package delivery.glovo.mappers;

import delivery.glovo.dto.OrderDto;
import delivery.glovo.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(uses = {ProductMapper.class}, componentModel = "spring")
public interface OrderMapper {
    Order orderDtoToOrder(OrderDto dto);
    OrderDto orderToOrderDto(Order order);
    List<OrderDto> toOrderDtoList(Iterable<Order> orders);
    @Mappings({
            @Mapping(target = "id", source = "order.id"),
            @Mapping(target = "date", source = "orderDto.date"),
            @Mapping(target = "cost", source = "orderDto.cost"),
            @Mapping(target = "products", source = "orderDto.products")
    })
    Order updateToOrder(Order order, OrderDto orderDto);


}
