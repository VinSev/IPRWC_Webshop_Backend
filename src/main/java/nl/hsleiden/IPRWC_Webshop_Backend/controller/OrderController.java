package nl.hsleiden.IPRWC_Webshop_Backend.controller;

import nl.hsleiden.IPRWC_Webshop_Backend.model.Order;
import nl.hsleiden.IPRWC_Webshop_Backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Order> getAll() {
        return this.orderService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void post(@Valid @RequestBody Order order) {
        this.orderService.create(order);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void put(@Valid @RequestBody Order order) {
        this.orderService.update(order);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void delete(@Valid @RequestBody Order order) {
        this.orderService.delete(order);
    }
}
