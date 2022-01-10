package com.nttdata.bootcamp.microservicio01.bussiness;

import com.nttdata.bootcamp.microservicio01.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<Product> create(Product product);
    Flux<Product> findAll();
    Mono<Product> findByIdProduct(String productId);
    Flux<Product> findBytype(String type);
    Mono<Product> update(Product product);

}
