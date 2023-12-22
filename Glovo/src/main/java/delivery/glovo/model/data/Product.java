package delivery.glovo.model.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("products")
public class Product {
    @Id
    private Integer id;
    private String name;
    private Double cost;

}
