package com.nttdata.bootcamp.microservicio01.repository;


import com.nttdata.bootcamp.microservicio01.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Flux<Product> findProductByTypeProduct(String type);
}
