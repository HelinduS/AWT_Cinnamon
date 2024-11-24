package com.example.demo.Repository;

import com.example.demo.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

    // Custom query method to find products based on availability
    List<Products> findByStockAvailability(boolean stockAvailability);

    // Example: Find products by name
    List<Products> findByProductNameContaining(String productName);
}