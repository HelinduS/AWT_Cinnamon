package com.example.demo.Service;

import com.example.demo.DTO.DeliveryDTO;
import com.example.demo.DTO.OrderDeliveryDTO;
import com.example.demo.Entity.Customer;
import com.example.demo.Entity.Delivery;
import com.example.demo.Entity.Order;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Repository.DeliveryRepository;
import com.example.demo.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Add a new delivery
    public Delivery addDelivery(DeliveryDTO deliveryDTO) {
        Delivery delivery = new Delivery();

        // Fetch the Order entity and set it
        orderRepository.findById(deliveryDTO.getOrderId()).ifPresentOrElse(
                delivery::setOrder,
                () -> {
                    throw new RuntimeException("Order not found with ID: " + deliveryDTO.getOrderId());
                }
        );

        delivery.setDescription(deliveryDTO.getDescription());
        delivery.setStatus(deliveryDTO.getStatus());
        delivery.setDate(deliveryDTO.getDate());

        return deliveryRepository.save(delivery);
    }
    // Fetch deliveries by customer ID, including both order and delivery details
    public List<OrderDeliveryDTO> getDeliveriesByCustomerId(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

        // Fetch orders by customer
        List<Order> orders = orderRepository.findByCustomer(customer);

        // Create a list of DTOs that include both order and delivery details
        List<OrderDeliveryDTO> orderDeliveryDTOs = orders.stream()
                .map(order -> {
                    // Get associated delivery
                    Delivery delivery = order.getDelivery();

                    if (delivery != null) {
                        return new OrderDeliveryDTO(
                                order.getOrderId(),
                                order.getDescription(),  // Adjust as needed based on your Order entity's fields
                                customer.getFirstName() + " " + customer.getLastName(), // Assuming you want the full name
                                delivery.getDeliveryId(),
                                delivery.getDescription(),
                                delivery.getStatus(),
                                delivery.getDate()
                        );
                    } else {
                        // In case the order does not have an associated delivery
                        return new OrderDeliveryDTO(
                                order.getOrderId(),
                                order.getDescription(),
                                customer.getFirstName() + " " + customer.getLastName(),
                                -1,   // Indicate no delivery with -1 or any other logic
                                "No delivery",
                                "Pending",
                                "TBD"
                        );
                    }
                })
                .collect(Collectors.toList());

        return orderDeliveryDTOs;
    }

    // Method to update delivery status using deliveryId and status
    public Delivery updateDeliveryStatus(int deliveryId, String status) {
        Delivery existingDelivery = deliveryRepository.findById(deliveryId).orElse(null);

        if (existingDelivery != null) {
            existingDelivery.setStatus(status);  // Update the status
            return deliveryRepository.save(existingDelivery);  // Save the updated delivery
        } else {
            return null;  // Return null if the delivery was not found
        }
    }



}