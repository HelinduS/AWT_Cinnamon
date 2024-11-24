package com.example.demo.Service;

import com.example.demo.Entity.Admin;
import com.example.demo.Entity.Products;
import com.example.demo.Repository.AdminRepository;
import com.example.demo.Repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductsRepository productsRepository;
    private final AdminRepository adminRepository; // Admin repository instance


    @Autowired
    public ProductService(ProductsRepository productsRepository, AdminRepository adminRepository) {
        this.productsRepository = productsRepository;
        this.adminRepository = adminRepository;
    }

    // CRUD operations using the repository

    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    public Products saveProduct(Products product) {
        return productsRepository.save(product);
    }

    public List<Products> getAvailableProducts() {
        return productsRepository.findByStockAvailability(true);
    }

    public void deleteProduct(Long id) {
        productsRepository.deleteById(id);
    }

    public Products addProductByAdmin(Products product, Long adminId) {
        // Use the instance variable adminRepository
        Optional<Admin> adminOptional = adminRepository.findById(adminId);

        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            product.setAdmin(admin); // Set the admin for the product
            return productsRepository.save(product);
        } else {
            throw new RuntimeException("Admin not found with ID: " + adminId);
        }
    }
}