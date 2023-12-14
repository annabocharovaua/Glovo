package delivery.glovo.service;

import delivery.glovo.dto.OrderDto;
import delivery.glovo.dto.ProductDto;
import delivery.glovo.repository.jdbc.JDBCRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GlovoServiceImpl implements GlovoService {

    private List<OrderDto> orders;
    private final JDBCRepository jdbcRepository;

    @Override
    public OrderDto getOrder(int id) {
        return jdbcRepository.getOrderById(id);
    }

    @Override
    public List<OrderDto> getOrders() {
        return jdbcRepository.getAllOrders();
    }

    @Override
    public void addOrder(OrderDto order) {
        jdbcRepository.saveOrder(order);
    }

    @Override
    public void updateOrder(int id, OrderDto updatedOrder) {
        jdbcRepository.updateOrderById(id, updatedOrder);
    }

    @Override
    public void deleteOrder(int id) {
        jdbcRepository.deleteOrderById(id);
    }

    @Override
    public ProductDto getProduct(int id) {
        return jdbcRepository.getProductById(id);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return jdbcRepository.getProducts();
    }

    @Override
    public void addProduct(ProductDto product) {
        jdbcRepository.saveProduct(product);
    }

    @Override
    public void updateProduct(int id, ProductDto product) {
        jdbcRepository.updateProductById(id, product);
    }

    @Override
    public void deleteProduct(int id) {
        jdbcRepository.deleteProductById(id);
    }

}
