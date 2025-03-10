package com.cs2.veterinaria.app.adapters.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs2.veterinaria.app.adapters.order.entity.OrderEntity;
import com.cs2.veterinaria.app.adapters.order.repository.OrderRepository;
import com.cs2.veterinaria.app.domains.model.Order;
import com.cs2.veterinaria.app.ports.OrderPort;

@Service
public class OrderAdapter implements OrderPort {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public boolean existOrder(int idOrder) {
        return orderRepository.existsById(idOrder);
    }

    @Override
    public void saveOrder(Order order) {
        OrderEntity orderEntity = new OrderEntity(order);
        orderRepository.save(orderEntity);
        order.setIdOrder(orderEntity.getIdOrder());
    }

    public Order createOrder(Order order) {
        OrderEntity orderEntity = new OrderEntity(order);
        orderRepository.save(orderEntity);
        return adapterOrder(orderEntity);
    }

    private Order adapterOrder(OrderEntity orderEntity) {
        Order order = new Order();
        order.setIdOrder(orderEntity.getIdOrder());
        order.setIdVerterinarian(orderEntity.getIdVeterinarian());
        order.setIdPet(orderEntity.getIdPet());
        order.setIdOwner(orderEntity.getIdOwner());
        order.setDateOrder(orderEntity.getDateOrder());
        order.setDrugName(orderEntity.getDrugName());
        return order;
    }
}
