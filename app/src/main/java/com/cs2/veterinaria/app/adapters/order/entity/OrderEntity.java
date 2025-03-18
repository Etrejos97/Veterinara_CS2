package com.cs2.veterinaria.app.adapters.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "id_pet")
    private long idPet;
    @Column(name = "id_owner")
    private long idOwner;
    @Column(name = "id_veterinarian")
    private long idVeterinarian;
    @Column(name = "date_order")
    private String dateOrder;
    @Column(name = "drug_name")
    private String drugName;

    public OrderEntity(Order order) {
        this.idOrder = order.getIdOrder();
        this.idPet = order.getIdPet();
        this.idOwner = order.getIdOwner();
        this.idVeterinarian = order.getIdVerterinarian();
        this.dateOrder = order.getDateOrder();
        this.drugName = order.getDrugName();
    }


    


}