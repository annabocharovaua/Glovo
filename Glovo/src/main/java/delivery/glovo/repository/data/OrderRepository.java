package delivery.glovo.repository.data;

import org.springframework.data.repository.CrudRepository;
import delivery.glovo.model.data.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
}
