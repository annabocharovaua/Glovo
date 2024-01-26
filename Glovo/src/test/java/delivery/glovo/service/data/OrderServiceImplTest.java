//package delivery.glovo.service.data;
//
//import delivery.glovo.dto.OrderDto;
//import delivery.glovo.dto.ProductDto;
//import delivery.glovo.mappers.OrderMapper;
//import delivery.glovo.model.Order;
//import delivery.glovo.repository.OrderRepository;
//import delivery.glovo.service.OrderServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class OrderServiceImplTest {
//    private static final int ORDER_ID = 100;
//    @InjectMocks
//    private OrderServiceImpl testInstance;
//    @Mock
//    private OrderRepository orderRepository;
//    @Mock
//    private OrderMapper orderMapper;
//    @Mock
//    private Order order;
//    @Mock
//    private List<Order> orders = new ArrayList<>();
//    private OrderDto orderDto;
//    private List<OrderDto> orderDtoList;
//
//    @BeforeEach
//    public void init() {
//        orderDto = OrderDto.builder().
//                id(ORDER_ID).
//                date(LocalDate.now()).
//                cost(15.5).
//                products(List.of(new ProductDto())).
//                build();
//
//        orderDtoList = new ArrayList<>();
//        orderDtoList.add(orderDto);
//    }
//
//    @Test
//    void shouldReturnOrderById() {
//        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
//        when(orderMapper.orderToOrderDto(order)).thenReturn(orderDto);
//
//        OrderDto result = testInstance.getOrderById(ORDER_ID);
//
//        verify(orderRepository).findById(ORDER_ID);
//        verify(orderMapper).orderToOrderDto(order);
//        assertNotNull(result);
//        assertEquals(ORDER_ID, result.getId());
//    }
//
//    @Test
//    void shouldNotReturnOrderById() {
//        assertThrows(NoSuchElementException.class, () -> {
//            testInstance.getOrderById(ORDER_ID);
//        });
//    }
//
//    @Test
//    void shouldReturnOrders() {
//        when(orderRepository.findAll()).thenReturn(orders);
//        when(orderMapper.toOrderDtoList(orders)).thenReturn(orderDtoList);
//
//        List<OrderDto> result = testInstance.getOrders();
//
//        verify(orderRepository).findAll();
//        verify(orderMapper).toOrderDtoList(orders);
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertEquals(orderDtoList, result);
//    }
//
//    @Test
//    void shouldSaveOrder() {
//        when(orderMapper.orderDtoToOrder(orderDto)).thenReturn(order);
//
//        testInstance.saveNewOrder(orderDto);
//
//        verify(orderMapper).orderDtoToOrder(orderDto);
//        verify(orderRepository).save(order);
//    }
//
//    @Test
//    void shouldUpdateOrder() {
//        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
//        when(orderMapper.updateToOrder(order, orderDto)).thenReturn(new Order());
//
//        testInstance.updateOrderById(ORDER_ID, orderDto);
//
//        verify(orderRepository).findById(ORDER_ID);
//        verify(orderMapper).updateToOrder(order, orderDto);
//        verify(orderRepository).save(new Order());
//    }
//
//    @Test
//    void shouldDeleteOrder() {
//        testInstance.deleteOrderById(ORDER_ID);
//
//        verify(orderRepository).deleteById(ORDER_ID);
//    }
//
//}