package com.cs2.veterinaria.app.adapters.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cs2.veterinaria.app.adapters.order.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    OrderEntity findByIdOrder(long orderId);

    boolean existsByIdOrder(long idOrder);
}
