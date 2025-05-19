package com.cs2.veterinaria.app.adapters.rest.request;

import com.cs2.veterinaria.app.domains.model.Product;

public class SellMedicineRequest {
    private int orderId;
    private Product medicine;

    // Getters y Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Product getMedicine() {
        return medicine;
    }

    public void setMedicine(Product medicine) {
        this.medicine = medicine;
    }
}
