
// Corrected OrderDTO
package com.example.demo.DTO;

public class OrderDTO {

    private int orderId;
    private int customerId;
    private String date;
    private String status;

    public OrderDTO() {}

    public OrderDTO(int orderId, int customerId, String date, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.date = date;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}