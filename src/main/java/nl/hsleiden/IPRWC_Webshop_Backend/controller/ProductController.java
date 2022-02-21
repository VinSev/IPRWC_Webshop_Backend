package nl.hsleiden.IPRWC_Webshop_Backend.controller;

import nl.hsleiden.IPRWC_Webshop_Backend.model.Product;
import nl.hsleiden.IPRWC_Webshop_Backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Product> getAll() {
        return this.productService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Product get(@PathVariable("id") Long id) {
        return this.productService.get(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void post(@Valid @RequestBody Product product) {
        this.productService.create(product);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void put(@Valid @RequestBody Product product) {
        this.productService.update(product);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void delete(@Valid @RequestBody Product product) {
        this.productService.delete(product);
    }
}
