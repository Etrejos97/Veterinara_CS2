package com.cs2.veterinaria.app.adapters.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.cs2.veterinaria.app.adapters.pet.entity.PetEntity;
import com.cs2.veterinaria.app.adapters.petOwner.entity.PetOwnerEntity;
import com.cs2.veterinaria.app.adapters.users.entity.UserEntity;
import com.cs2.veterinaria.app.domains.model.Order;

@Entity
@Table(name = "order")
@Setter
@Getter
@NoArgsConstructor
public class OrderEntity {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_order")
    private long idOrder;

    @JoinColumn(name="id_pet")
    @OneToOne
    private PetEntity pet;

    @JoinColumn(name = "id_owner")
    @OneToOne
    private PetOwnerEntity owner;

    @JoinColumn(name = "user_id")
    @OneToOne
    private UserEntity user;
    //user_id
    @Column(name = "date_order")
    private String dateOrder;
    @Column(name = "drug_name")
    private String drugName;

    public OrderEntity(Order order, PetEntity petEntity, PetOwnerEntity petOwnerEntity, UserEntity userEntity) {
        this.idOrder = order.getIdOrder();
        this.pet = petEntity;
        this.owner = petOwnerEntity;
        this.user = userEntity;
        this.dateOrder = order.getDateOrder();
        this.drugName = order.getDrugName();
    }


    


}