package delivery.glovo.mappers;

import delivery.glovo.dto.OrderDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<OrderDto> {
    @Override
    public OrderDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderDto order = new OrderDto();
        order.setId(rs.getInt("id"));
        order.setDate(rs.getDate("date").toLocalDate());
        order.setCost(rs.getDouble("cost"));

        return order;
    }
}
