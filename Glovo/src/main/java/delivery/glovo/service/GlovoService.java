package delivery.glovo.service;

import delivery.glovo.dto.OrderDto;
import delivery.glovo.dto.ProductDto;

import java.util.List;

public interface GlovoService {
    public OrderDto getOrder(int id);

    public List<OrderDto> getOrders();

    public void addOrder(OrderDto order);

    public void updateOrder(int id, OrderDto updatedOrder);

    public void deleteOrder(int id);

    public ProductDto getProduct(int id);

    public List<ProductDto> getAllProducts();

    public void addProduct(ProductDto product);

    public void updateProduct(int id, ProductDto product);

    public void deleteProduct(int id);
}
