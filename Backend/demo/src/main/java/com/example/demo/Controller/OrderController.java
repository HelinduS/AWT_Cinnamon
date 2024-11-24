// Corrected OrderController
package com.example.demo.Controller;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.Entity.Delivery;
import com.example.demo.Entity.Order;
import com.example.demo.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO) {
        try {
            orderService.saveOrder(orderDTO);
            return ResponseEntity.ok("Order created successfully");
        } catch (Exception e) {
            e.printStackTrace();  // Log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating order: " + e.getMessage());
        }
    }


    @GetMapping("/findByCustomer")
    public ResponseEntity<?> getOrdersByCustomerId(@RequestParam int customerId) {
        try {
            List<OrderDTO> orders = orderService.getOrdersByCustomerId(customerId);
            return ResponseEntity.ok(orders);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/update-status")
    public ResponseEntity<Order> updateOrderStatus(
            @RequestParam int orderId,  // Corrected to "orderId"
            @RequestParam String status) { // status as query parameter

        Order updatedOrder = orderService.updateOrderStatus(orderId, status);

        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
