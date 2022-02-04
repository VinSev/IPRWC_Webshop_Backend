package nl.hsleiden.IPRWC_Webshop_Backend.dao;

import nl.hsleiden.IPRWC_Webshop_Backend.dao.Repository.ProductRepository;
import nl.hsleiden.IPRWC_Webshop_Backend.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDao implements Dao<Product, Long> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product get(Long id) {
        return this.productRepository.getById(id);
    }

    @Override
    public Product create(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        this.productRepository.update(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImageLink()
        );
    }

    @Override
    public void delete(Product product) {
        this.productRepository.delete(product);
    }
}
