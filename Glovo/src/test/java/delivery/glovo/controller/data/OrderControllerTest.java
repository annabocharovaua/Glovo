//package delivery.glovo.controller.data;
//
//import delivery.glovo.controller.response.ApiResponse;
//import delivery.glovo.model.Order;
//import delivery.glovo.repository.OrderRepository;
//import delivery.glovo.model.Product;
//import delivery.glovo.dto.OrderDto;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//
//import org.springframework.test.context.ActiveProfiles;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//
//@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class OrderControllerTest {
//
//    @Value("${local.server.port}")
//    private int port;
//
//    @Autowired
//    private TestRestTemplate testRestTemplate;
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Test
//    void shouldGetOrderById() {
//        testRestTemplate = testRestTemplate.withBasicAuth("user@gmail.com", "password");
//        Order order = Order.builder().
//                date(LocalDate.now()).
//                cost(8.256).
//                products(List.of(Product.builder().
//                        name("newProduct").
//                        cost(8.256).
//                        build()))
//                .build();
//        Order savedOrder = orderRepository.save(order);
//
//        OrderDto result = testRestTemplate.getForObject("http://localhost:" + port + "/api/v1/orders/" + savedOrder.getId(), OrderDto.class);
//
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals(savedOrder.getId(), result.getId());
//    }
//
//    @Test
//    void shouldCreateOrder() {
//        List<Order> initialOrders = orderRepository.findAll();
//        int initialSize = initialOrders.size();
//        Order order = Order.builder().
//                date(LocalDate.now()).
//                cost(100.5).
//                products(List.of(Product.builder().
//                        name("product").
//                        cost(100.5).
//                        build()))
//                .build();
//
//        testRestTemplate.postForEntity("http://localhost:" + port + "/api/v1/orders", order, Order.class);
//
//        List<Order> updatedOrders = orderRepository.findAll();
//        int updatedSize = updatedOrders.size();
//
//        Assertions.assertEquals(initialSize + 1, updatedSize);
//    }
//
//    @Test
//    void shouldDeleteOrderById() {
//        Order order = Order.builder().
//                date(LocalDate.now()).
//                cost(100.5).
//                products(List.of(Product.builder().
//                        name("product").
//                        cost(100.5).
//                        build()))
//                .build();
//        Order savedOrder = orderRepository.save(order);
//        List<Order> initialOrders = orderRepository.findAll();
//        int initialSize = initialOrders.size();
//
//        testRestTemplate.delete("http://localhost:" + port + "/api/v1/orders/" + savedOrder.getId());
//
//        List<Order> updatedOrders = orderRepository.findAll();
//        int updatedSize = updatedOrders.size();
//        Optional<Order> deletedOrder = orderRepository.findById(savedOrder.getId());
//
//        Assertions.assertEquals(initialSize - 1, updatedSize);
//        Assertions.assertTrue(deletedOrder.isEmpty());
//
//    }
//
//    @Test
//    void shouldUpdateOrderById() {
//        Order order = Order.builder().
//                date(LocalDate.now()).
//                cost(75.75).
//                products(List.of(Product.builder().
//                                name("product1").
//                                cost(25.25).
//                                build(),
//                        Product.builder().
//                                name("product2").
//                                cost(40.5).
//                                build()))
//                .build();
//        Order savedOrder = orderRepository.save(order);
//        OrderDto updatedOrderDto = OrderDto.builder().cost(65.5).date(LocalDate.now()).build();
//
//        testRestTemplate.exchange("http://localhost:" + port + "/api/v1/orders/" + savedOrder.getId(), HttpMethod.PUT,
//                new HttpEntity<>(updatedOrderDto),
//                new ParameterizedTypeReference<ApiResponse<OrderDto>>() {
//                });
//        Optional<Order> updatedOrder = orderRepository.findById(savedOrder.getId());
//
//        Assertions.assertEquals(65.5, updatedOrder.get().getCost());
//    }
//
//}