// Corrected OrderController
package com.example.demo.Controller;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO) {
        orderService.saveOrder(orderDTO);
        return ResponseEntity.ok("Order created successfully");
    }

    @GetMapping("/find")
    public ResponseEntity<?> getOrderById(@RequestParam int orderId) {
        try {
            OrderDTO orderDTO = orderService.getOrderById(orderId);
            return ResponseEntity.ok(orderDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}