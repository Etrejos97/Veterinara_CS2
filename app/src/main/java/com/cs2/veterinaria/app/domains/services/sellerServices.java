package com.cs2.veterinaria.app.domains.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs2.veterinaria.app.domains.model.Order;
import com.cs2.veterinaria.app.domains.model.Product;
import com.cs2.veterinaria.app.domains.model.Bill;
import com.cs2.veterinaria.app.domains.model.HistoryClinic;
import com.cs2.veterinaria.app.ports.OrderPort;
import com.cs2.veterinaria.app.ports.ProductPort;
import com.cs2.veterinaria.app.ports.BillPort;
import com.cs2.veterinaria.app.ports.ClinicalPort;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Service
public class SellerServices {
    @Autowired
    private OrderPort orderPort;
    @Autowired
    private ProductPort productPort;
    @Autowired
    private BillPort billPort;
    @Autowired
    private ClinicalPort clinicalPort;

    // Método para consultar todas las órdenes médicas
    public List<Order> getAllOrders() {
        return orderPort.findAllOrders();
    }

    // Método para vender un producto genérico
    public void sellProduct(Product product) throws Exception {
        productPort.sellProduct(product);
        generateBill(null, product);
    }

    // Método para vender un medicamento
    public void sellMedicine(int orderId, Product medicine) throws Exception {
        // Verificar si la orden existe
        if (!orderPort.existOrder(orderId)) {
            throw new Exception("No existe una orden con ese ID");
        }
        // Vender el medicamento
        productPort.sellProduct(medicine);

        // Registrar la venta en la historia clínica
        Order order = orderPort.findById(orderId);
        HistoryClinic history = new HistoryClinic();
        history.setIdPet(order.getIdPet());
        history.setDetails("Medicamento vendido: " + medicine.getProductName());
        clinicalPort.saveHistory(history);

        // Generar factura
        generateBill(order, medicine);
    }

    // Método para generar una factura
    private void generateBill(Order order, Product product) {
            Bill bill = new Bill();
            if (order != null) {
                bill.setIdOrder(order.getIdOrder());
                bill.setIdPet(order.getIdPet());
                bill.setIdOwner(order.getIdOwner());
        }
        bill.setDateCreated(new Date().toString());
        bill.setPrice(product.getPrice());
        billPort.saveBill(bill);
    }

    
}
