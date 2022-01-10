package com.nttdata.bootcamp.microservicio01.bussiness.impl;

import com.nttdata.bootcamp.microservicio01.bussiness.ProductService;
import com.nttdata.bootcamp.microservicio01.model.Customer;
import com.nttdata.bootcamp.microservicio01.model.Product;
import com.nttdata.bootcamp.microservicio01.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Mono<Product> create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Flux<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Flux<Product> findBytype(String type) {
        return productRepository.findProductByTypeProduct(type);
    }

    @Override
    public Mono<Product> update(Product product) {
        return productRepository.findById(product.getId())
                .flatMap(productDB -> {
                    return create(product);
                })
                .switchIfEmpty(Mono.empty());
    }
    @Override
    public Mono<Product> findByIdProduct(String productId) {
        return productRepository.findById(productId);
    }
}
