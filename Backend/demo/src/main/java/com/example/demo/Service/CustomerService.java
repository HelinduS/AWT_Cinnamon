package com.example.demo.Service;

import com.example.demo.DTO.CustomerDTO;
import com.example.demo.Entity.Customer;
import com.example.demo.Entity.LoginInfo;
import com.example.demo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {


    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(CustomerDTO customerDTO) {
        // Create a new Customer entity and map fields from the DTO
        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setUserName(customerDTO.getUserName());
        customer.setAddress(customerDTO.getAddress());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword());

        // Create a new LoginInfo entity and map relevant fields
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserName(customerDTO.getUserName());
        loginInfo.setEmail(customerDTO.getEmail());
        loginInfo.setPassword(customerDTO.getPassword());

        // Link the LoginInfo entity to the Customer
        customer.setLoginInfo(loginInfo);

        // Save the customer (which will also save the LoginInfo due to CascadeType.ALL)
        return customerRepository.save(customer);
    }

    public CustomerDTO getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found with email: " + email));

        return new CustomerDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getUserName(),
                customer.getAddress(),
                customer.getEmail(),
                customer.getPassword()
        );
    }


}
