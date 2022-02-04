package nl.hsleiden.IPRWC_Webshop_Backend.dao.Repository;

import nl.hsleiden.IPRWC_Webshop_Backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Updates a Product.
     * @param id Id of the Product
     * @param name Name of the Product
     * @param description Description of the Product
     * @param price Price of the Product
     * @author Vincent Severin
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE product SET name = :name, description = :description, price = :price, image_link = :imageLink WHERE id = :id",
            nativeQuery = true)
    void update(@Param("id") Long id,
                @Param("name") String name,
                @Param("description") String description,
                @Param("price") float price,
                @Param("imageLink") String imageLink);

    @Query(value = "SELECT product.*\n" +
            "FROM user_order_item \n" +
            "JOIN product ON user_order_item.product_id = product.id\n" +
            "WHERE order_id = :orderId",
            nativeQuery = true)
    List<Product> getOrderItems(@Param("orderId") Long orderId);
}
