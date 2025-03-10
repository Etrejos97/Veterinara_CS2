package com.cs2.veterinaria.app.domains.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs2.veterinaria.app.domains.model.Product;
import com.cs2.veterinaria.app.ports.OrderPort;
import com.cs2.veterinaria.app.ports.ProductPort;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Service
public class sellerServices {
    @Autowired
    private OrderPort orderPort;
    @Autowired
    private ProductPort productPort;

    public void sellProduct(int orderId, Product product) throws Exception {
        // Verificar si la orden existe
        if (!orderPort.existOrder(orderId)) {
            throw new Exception("No existe una orden con ese ID");
        }
        // Vender el producto
        productPort.sellProduct(product);
    }

    public void sellMedicine(int orderId, Product medicine) throws Exception {
        // Verificar si la orden existe
        if (!orderPort.existOrder(orderId)) {
            throw new Exception("No existe una orden con ese ID");
        }
        // Vender el medicamento
        productPort.sellProduct(medicine);
    }
}
