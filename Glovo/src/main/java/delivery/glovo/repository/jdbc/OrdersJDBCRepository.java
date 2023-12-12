package delivery.glovo.repository.jdbc;

import delivery.glovo.dto.OrderDto;
import delivery.glovo.mappers.OrderRowMapper;
import delivery.glovo.mappers.ProductRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrdersJDBCRepository {

    private final JdbcTemplate jdbcTemplate;
    private final String SELECT_ORDER_BY_ID = "SELECT * FROM orders WHERE id = ";
    private final String SELECT_PRODUCTS_FOR_ORDER_BY_ID = "SELECT products.* " +
            "FROM products" +
            " JOIN order_product ON products.id = order_product.id_product" +
            " AND order_product.id_order = ";
    private final String SELECT_ALL_ORDERS = "SELECT * FROM orders";
    private final String SAVE_NEW_ORDER = "INSERT INTO orders (id, date, cost) VALUES(?, ?, ?)";
    private final String UPDATE_ORDER_BY_ID = "UPDATE orders SET date = ?, cost = ? WHERE id = ?";
    private final String DELETE_ORDER_BY_ID = "DELETE FROM orders WHERE id = ";

    public OrderDto getById(int id) {
        OrderDto orderDto = jdbcTemplate.queryForObject(SELECT_ORDER_BY_ID + id, new OrderRowMapper());
        orderDto.setProducts(jdbcTemplate.query(SELECT_PRODUCTS_FOR_ORDER_BY_ID + id, new ProductRowMapper()));
        return orderDto;
    }

    public List<OrderDto> getAll() {
        List<OrderDto> orderDtoList = jdbcTemplate.query(SELECT_ALL_ORDERS, new OrderRowMapper());
        for (OrderDto order : orderDtoList) {
            order.setProducts(jdbcTemplate.query(SELECT_PRODUCTS_FOR_ORDER_BY_ID + order.getId(), new ProductRowMapper()));
        }
        return orderDtoList;
    }

    public void save(OrderDto order) {
        jdbcTemplate.update(SAVE_NEW_ORDER, order.getId(), order.getDate(), order.getCost());
    }

    public void updateById(int id, OrderDto order) {
        jdbcTemplate.update(UPDATE_ORDER_BY_ID, order.getDate(), order.getCost(), id);
    }

    public void deleteById(int id) {
        jdbcTemplate.update(DELETE_ORDER_BY_ID + id);
    }
}
