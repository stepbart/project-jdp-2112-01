package com.kodilla.ecommercee.service;


import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(final Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProduct(final long productId) {
        return productRepository.findById(productId);
    }

    public void removeProduct(final long productId) {
        productRepository.deleteById(productId);
    }
}
