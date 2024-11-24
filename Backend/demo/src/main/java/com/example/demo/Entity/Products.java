package com.example.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @Column(nullable = false)
    @NotNull
    private String productName;

    @Column(nullable = false)
    @NotNull
    private double price;

    @Column(nullable = false)
    private boolean stockAvailability;

    @Column(length = 500)
    @Size(max = 500)
    private String description;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id", nullable = false)
    private Admin admin;

    // Default Constructor
    public Products() {}

    // All-Args Constructor
    public Products(long productId, String productName, double price, boolean stockAvailability, String description, String imageUrl, Admin admin) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stockAvailability = stockAvailability;
        this.description = description;
        this.imageUrl = imageUrl;
        this.admin = admin;
    }

    // Constructor without productId
    public Products(String productName, double price, boolean stockAvailability, String description, String imageUrl, Admin admin) {
        this.productName = productName;
        this.price = price;
        this.stockAvailability = stockAvailability;
        this.description = description;
        this.imageUrl = imageUrl;
        this.admin = admin;
    }

    // Getters and Setters
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStockAvailability() {
        return stockAvailability;
    }

    public void setStockAvailability(boolean stockAvailability) {
        this.stockAvailability = stockAvailability;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Products{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", stockAvailability=" + stockAvailability +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", admin=" + admin +
                '}';
    }
}