package com.example.demo.Repository;

import com.example.demo.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // Existing method
    Optional<Customer> findByEmail(String email);

    // Additional methods if needed
    boolean existsByEmail(String email);

    @Query("SELECT c FROM Customer c WHERE c.email = :email")
    Customer findCustomerByEmail(@Param("email") String email);
}