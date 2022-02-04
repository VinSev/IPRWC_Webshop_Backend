package nl.hsleiden.IPRWC_Webshop_Backend.service;

import nl.hsleiden.IPRWC_Webshop_Backend.dao.ProductDao;
import nl.hsleiden.IPRWC_Webshop_Backend.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDAO;

    public List<Product> getAll() {
        return this.productDAO.getAll();
    }

    public Product get(Long id) {
        return this.productDAO.get(id);
    }

    public void create(Product product) {
        this.productDAO.create(product);
    }

    public void update(Product product) {
        this.productDAO.update(product);
    }

    public void delete(Product product) {
        this.productDAO.delete(product);
    }
}
