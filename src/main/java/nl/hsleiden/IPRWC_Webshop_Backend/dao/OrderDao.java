package nl.hsleiden.IPRWC_Webshop_Backend.dao;

import nl.hsleiden.IPRWC_Webshop_Backend.dao.Repository.OrderItemRepository;
import nl.hsleiden.IPRWC_Webshop_Backend.dao.Repository.OrderRepository;
import nl.hsleiden.IPRWC_Webshop_Backend.dao.Repository.ProductRepository;
import nl.hsleiden.IPRWC_Webshop_Backend.model.Order;
import nl.hsleiden.IPRWC_Webshop_Backend.model.OrderItem;
import nl.hsleiden.IPRWC_Webshop_Backend.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class OrderDao implements Dao<Order, Long> {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Order> getAll() {
        return this.orderRepository.findAll();
    }

    public List<Order> getAllFromUser(String userId) {
        return this.orderRepository.findAllByUserId(userId);
    }

    @Override
    public Order get(Long id) {
        return this.orderRepository.getById(id);
    }

    @Override
    @Transactional
    public Order create(Order order) {
        this.orderRepository.saveAndFlush(order);
        for (Product product : order.getProducts()) {
            this.orderItemRepository.save(new OrderItem(order.getId(), product.getId(), product.getAmount()));
        }
        return order;
    }

    @Override
    public void update(Order order) {
        this.orderRepository.update(
                order.getId(),
                order.getUserId(),
                order.getStreetName(),
                order.getHouseNumber(),
                order.getPostcode(),
                order.getPlaceName()
        );
    }

    @Override
    public void delete(Order order) {
        this.orderRepository.delete(order);
    }

    public List<Product> getOrderItems(Long orderId) {
        List<Product> products = this.productRepository.getOrderItems(orderId);
        List<Integer> amounts = this.orderRepository.getOrderItemsAmount(orderId);
        for(int i = 0; i < products.size(); i++) {
            products.get(i).setAmount(amounts.get(i));
        }
        return products;
    }
}
