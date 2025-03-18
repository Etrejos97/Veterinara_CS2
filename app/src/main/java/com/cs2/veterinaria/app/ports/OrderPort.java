package com.cs2.veterinaria.app.ports;

import com.cs2.veterinaria.app.domains.model.Order;
import java.util.List;

public interface OrderPort {

    void saveOrder(Order order);
    void cancelOrder(long orderId);
    boolean existOrder(long orderId);
    List<Order> getAllOrders();
    List<Order> findAllOrders();
    Order findById(long orderId);
}



