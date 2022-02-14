package nl.hsleiden.IPRWC_Webshop_Backend.dao.repository;

import nl.hsleiden.IPRWC_Webshop_Backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM user_order WHERE user_id = :userId",
            nativeQuery = true)
    List<Order> findAllByUserId(@Param("userId") String userId);

    @Query(value = "SELECT user_order_item.amount\n" +
            "FROM user_order_item \n" +
            "JOIN product ON user_order_item.product_id = product.id\n" +
            "WHERE order_id = :orderId",
            nativeQuery = true)
    List<Integer> getOrderItemsAmount(@Param("orderId") Long orderId);

    /**
     * Updates an Order
     * @param id Id of the Order
     * @param userid UserId of the Order
     * @param streetName StreetName of the Order
     * @param houseNumber HouseNumber of the Order
     * @param postcode Postcode of the Order
     * @param placeName PlaceName of the Order
     * @author Vincent Severin
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE product SET user_id = :userId, street_name = :streetName, house_number = :houseNumber, postcode = :postcode, place_name = :placeName WHERE id = :id",
            nativeQuery = true)
    void update(@Param("id") Long id,
                @Param("userId") Long userid,
                @Param("streetName") String streetName,
                @Param("houseNumber") int houseNumber,
                @Param("postcode") String postcode,
                @Param("placeName") String placeName);
}
