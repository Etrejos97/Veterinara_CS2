package com.cs2.veterinaria.app.ports;

import com.cs2.veterinaria.app.domains.model.Product;

public interface ProductPort {
    void sellProduct(Product product);
    public Product getProductById(Long productId);
}
