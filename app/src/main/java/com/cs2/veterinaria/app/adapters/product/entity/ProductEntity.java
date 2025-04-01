package com.cs2.veterinaria.app.adapters.product.entity;

import com.cs2.veterinaria.app.domains.model.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Setter
@Getter
@NoArgsConstructor
public class ProductEntity {
    @Id
    @Column(name="product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    @Column(name="product_name")
    private String productName;
    @Column(name="price")
    private double price;

    public ProductEntity(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.price = product.getPrice();
    }
}
