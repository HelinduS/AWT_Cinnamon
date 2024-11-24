package com.example.demo.Controller;

import com.example.demo.Service.CustomerService;
import com.example.demo.DTO.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.saveCustomer(customerDTO);
        return ResponseEntity.ok("Customer created successfully");
    }

    @GetMapping("/find")
    public ResponseEntity<?> getCustomerByEmail(@RequestParam String email) {
        try {
            CustomerDTO customerDTO = customerService.getCustomerByEmail(email);
            if (customerDTO != null) {
                return ResponseEntity.ok(customerDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error retrieving customer: " + e.getMessage());
        }
    }
}
