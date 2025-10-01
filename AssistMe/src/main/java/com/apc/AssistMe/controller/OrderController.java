package com.apc.AssistMe.controller;

import com.apc.AssistMe.model.Order;
import com.apc.AssistMe.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // GET ALL ORDERS
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // GET ORDER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PLACE ORDER
    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        // Calculate total = quantity * product.price
        order.setTotal(order.getQuantity() * order.getProduct().getPrice());
        return orderService.saveOrder(order);
    }

    // DELETE ORDER
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
