package com.cs2.veterinaria.app.ports;

import com.cs2.veterinaria.app.domains.model.Order;

public interface OrderPort {
    boolean existOrder(int idOrder);
    void saveOrder(Order order);
}
