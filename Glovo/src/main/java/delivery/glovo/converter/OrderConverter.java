package delivery.glovo.converter;

import delivery.glovo.dto.OrderDto;
import delivery.glovo.dto.ProductDto;
import delivery.glovo.model.data.Order;
import delivery.glovo.model.data.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

@Component
public class OrderConverter {
    public OrderDto fromModel(Order order) {
        return OrderDto.builder().
                id(order.getId()).
                date(order.getDate()).
                cost(order.getCost()).
                products(productsFromModel(order.getProducts())).
                build();
    }
    public Order toModel(OrderDto orderDto) {
        return Order.builder().
                id(orderDto.getId()).
                date(orderDto.getDate()).
                cost(orderDto.getCost()).
                products(productsToModel(orderDto.getProducts())).
                build();
    }

    public Order toModel(Order order, OrderDto orderDto) {
        order.setDate(orderDto.getDate());
        order.setCost(orderDto.getCost());
        order.setProducts(productsToModel(orderDto.getProducts()));
        return order;
    }
    public List<OrderDto> fromModel(Iterable<Order> orders) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : orders) {
            orderDtoList.add(fromModel(order));
        }
        return orderDtoList;
    }
    public List<ProductDto> productsFromModel(List<Product> productsList) {
        List<ProductDto> products = new ArrayList<>();
        if (isNotEmpty(productsList)) {
            for (Product product : productsList) {
                ProductDto productDto = ProductDto.builder().
                        id(product.getId()).
                        name(product.getName()).
                        cost(product.getCost()).
                        build();
                products.add(productDto);
            }
        }
        return products;
    }
    public List<Product> productsToModel(List<ProductDto> productsDtoList) {
        List<Product> products = new ArrayList<>();
        if (isNotEmpty(productsDtoList)) {
            for (ProductDto productDto : productsDtoList) {
                Product product = Product.builder().
                        id(productDto.getId()).
                        name(productDto.getName()).
                        cost(productDto.getCost()).
                        build();
                products.add(product);
            }
        }
        return products;
    }
}
