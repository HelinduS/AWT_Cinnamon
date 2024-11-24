package com.example.demo.Service;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.Entity.Customer;
import com.example.demo.Entity.Delivery;
import com.example.demo.Entity.Order;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public void saveOrder(OrderDTO orderDTO) {
        Customer customer = customerRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Order order = new Order(customer, orderDTO.getDate(), orderDTO.getDescription(), orderDTO.getStatus());
        orderRepository.save(order);
    }

    public List<OrderDTO> getOrdersByCustomerId(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

        List<Order> orders = orderRepository.findByCustomer(customer);
        return orders.stream()
                .map(order -> new OrderDTO(
                        order.getOrderId(),
                        order.getCustomer().getId(),
                        order.getDate(),
                        order.getDescription(),
                        order.getStatus()
                ))
                .collect(Collectors.toList());
    }

    public Order updateOrderStatus(int orderId, String status) {
        Order existingOrder = orderRepository.findById(orderId).orElse(null);

        if (existingOrder != null) {
            existingOrder.setStatus(status);  // Update the status of the order
            return orderRepository.save(existingOrder);  // Save and return the updated order
        } else {
            return null;  // Return null if the order was not found
        }
    }
}
