package nl.hsleiden.IPRWC_Webshop_Backend.dao.Repository;

import nl.hsleiden.IPRWC_Webshop_Backend.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
