package com.cs2.veterinaria.app.adapters.bill.entity;

import com.cs2.veterinaria.app.adapters.order.entity.OrderEntity;
import com.cs2.veterinaria.app.adapters.pet.entity.PetEntity;
import com.cs2.veterinaria.app.adapters.petOwner.entity.PetOwnerEntity;
import com.cs2.veterinaria.app.domains.model.Bill;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bill")
@Setter
@Getter
@NoArgsConstructor
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bill")
    private Long idBill;

    @JoinColumn(name="id_order")
    @OneToOne
    private OrderEntity order;

    @JoinColumn(name="id_pet")
    @OneToOne
    private PetEntity pet;

    @JoinColumn(name="id_owner")
    @OneToOne
    private PetOwnerEntity owner;

    @Column(name = "date_created")
    private String dateCreated;

    @Column(name = "price")
    private double price;

    public BillEntity(Bill bill,OrderEntity orderEntity, PetEntity petEntity, PetOwnerEntity petOwnerEntity) {
        this.idBill = bill.getIdBill();
        this.order = orderEntity;
        this.pet = petEntity;
        this.owner = petOwnerEntity;
        this.dateCreated = bill.getDateCreated();
        this.price = bill.getPrice();
    }

}
