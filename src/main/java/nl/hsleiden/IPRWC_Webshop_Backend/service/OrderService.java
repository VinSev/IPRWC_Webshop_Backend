package nl.hsleiden.IPRWC_Webshop_Backend.service;

import nl.hsleiden.IPRWC_Webshop_Backend.dao.OrderDao;
import nl.hsleiden.IPRWC_Webshop_Backend.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDAO;

    public List<Order> getAll() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Order> orders = this.orderDAO.getAllFromUser(email);
        for (Order order : orders) {
            order.setProducts(this.orderDAO.getOrderItems(order.getId()));
        }
        return orders;
    }

    public Order get(Long id) {
        return this.orderDAO.get(id);
    }

    public void create(Order order) {
        this.orderDAO.create(order);
    }

    public void update(Order order) {
        this.orderDAO.update(order);
    }

    public void delete(Order order) {
        this.orderDAO.delete(order);
    }
}
