package com.example.demo.Controller;

import com.example.demo.Entity.Products;
import com.example.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Products> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Products addProduct(@RequestBody Products product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/available")
    public List<Products> getAvailableProducts() {
        return productService.getAvailableProducts();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PostMapping("/admin/add")
    public Products addProductByAdmin(@RequestBody Products product, @RequestParam Long adminId) {
        return productService.addProductByAdmin(product, adminId);
    }

}