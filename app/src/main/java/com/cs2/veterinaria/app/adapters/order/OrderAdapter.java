package com.cs2.veterinaria.app.adapters.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs2.veterinaria.app.adapters.order.entity.OrderEntity;
import com.cs2.veterinaria.app.adapters.order.repository.OrderRepository;
import com.cs2.veterinaria.app.adapters.pet.entity.PetEntity;
import com.cs2.veterinaria.app.adapters.pet.repository.PetRepository;
import com.cs2.veterinaria.app.adapters.petOwner.entity.PetOwnerEntity;
import com.cs2.veterinaria.app.adapters.petOwner.repository.PetOwnerRepository;
import com.cs2.veterinaria.app.adapters.users.entity.UserEntity;
import com.cs2.veterinaria.app.adapters.users.repository.UserRepository;
import com.cs2.veterinaria.app.domains.model.Order;
import com.cs2.veterinaria.app.ports.OrderPort;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Service
public class OrderAdapter implements OrderPort {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PetRepository petRepository;

    
    @Autowired
    private PetOwnerRepository petOwnerRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean existOrder(long idOrder) {
        return orderRepository.existsByIdOrder(idOrder);
    }

    @Override
    public void saveOrder(Order order) {
        // Recuperar las entidades relacionadas
        PetEntity petEntity = petRepository.findById(order.getIdPet())
            .orElseThrow(() -> new RuntimeException("No existe una mascota con el ID especificado."));
        PetOwnerEntity petOwnerEntity = petOwnerRepository.findById(order.getIdOwner())
            .orElseThrow(() -> new RuntimeException("No existe un dueño con el ID especificado."));
        UserEntity userEntity = userRepository.findById(order.getUserId())
            .orElseThrow(() -> new RuntimeException("No existe un usuario con el ID especificado."));

        // Crear la entidad OrderEntity
        OrderEntity orderEntity = new OrderEntity(order, petEntity, petOwnerEntity, userEntity);

        // Guardar la orden en la base de datos
        orderRepository.save(orderEntity);
    }

    public Order createOrder(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderRepository.save(orderEntity);
        return adapterOrder(orderEntity);
    }

    private Order adapterOrder(OrderEntity orderEntity) {
        Order order = new Order();
        order.setIdOrder(orderEntity.getIdOrder());
        order.setUserId(orderEntity.getUser().getUserId());
        order.setIdPet(orderEntity.getPet().getIdPet());
        order.setIdOwner(orderEntity.getOwner().getIdOwner());
        order.setDateOrder(orderEntity.getDateOrder());
        order.setDrugName(orderEntity.getDrugName());
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return findAllOrders();
    }

    @Override
    public List<Order> findAllOrders() {
        List<OrderEntity> entities = orderRepository.findAll();
        return entities.stream()
            .map(this::convertToDomain)
            .toList();
    }

    @Override
    public void cancelOrder(long idOrder) {

        throw new UnsupportedOperationException();
    }
    
    @Override
    public Order findById(long orderId) {
        OrderEntity orderEntity = orderRepository.findByIdOrder(orderId);
        return convertToDomain(orderEntity);
    }

    private Order convertToDomain(OrderEntity orderEntity) {
        Order order = new Order();
        order.setIdOrder(orderEntity.getIdOrder());
        order.setIdPet(orderEntity.getPet().getIdPet());
        order.setIdOwner(orderEntity.getOwner().getIdOwner());
        order.setUserId(orderEntity.getUser().getUserId());
        order.setDateOrder(orderEntity.getDateOrder());
        order.setDrugName(orderEntity.getDrugName());
        return order;
    }
}
