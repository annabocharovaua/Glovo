package delivery.glovo.mappers;

import delivery.glovo.dto.ProductDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<ProductDto> {

    @Override
    public ProductDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        ProductDto product = new ProductDto();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setCost(rs.getDouble("cost"));

        return product;
    }
}
