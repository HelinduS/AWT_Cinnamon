package com.example.demo.Service;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.Entity.Customer;
import com.example.demo.Entity.Order;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public void saveOrder(OrderDTO orderDTO) {
        Customer customer = customerRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Order order = new Order(customer, orderDTO.getDate(), orderDTO.getStatus());
        orderRepository.save(order);
    }

    public OrderDTO getOrderById(int orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        return new OrderDTO(
                order.getOrderId(),
                order.getCustomer().getId(), // Fetching customerId correctly
                order.getDate(),
                order.getStatus()
        );
    }
}
