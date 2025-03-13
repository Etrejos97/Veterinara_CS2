package com.cs2.veterinaria.app.adapters.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cs2.veterinaria.app.ports.ProductPort;
import com.cs2.veterinaria.app.adapters.product.entity.ProductEntity;
import com.cs2.veterinaria.app.adapters.product.repository.ProductRepository;
import com.cs2.veterinaria.app.domains.model.Product;

@Service
public class ProductAdapter implements ProductPort {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public void sellProduct(Product product) {
        ProductEntity productEntity = new ProductEntity(product);
        productRepository.save(productEntity);
        product.setProductId(productEntity.getProductId());
    }
    @Override
    public Product getProductById(Long productId) {
        ProductEntity productEntity = productRepository.findById(productId).orElse(null);
        if (productEntity != null) {
            return adapterProduct(productEntity);
        }
        return null;
    }

    
    private Product adapterProduct(ProductEntity productEntity) {
        Product product = new Product();
        product.setProductId(productEntity.getProductId());
        product.setProductName(productEntity.getProductName());
        product.setPrice(productEntity.getPrice());
        return product;
    }
}
