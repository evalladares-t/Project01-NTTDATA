package com.nttdata.bootcamp.microservicio01.bussiness;

import com.nttdata.bootcamp.microservicio01.model.Customer;
import com.nttdata.bootcamp.microservicio01.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Mono<Customer> create(Customer customer);
    Flux<Customer> findAll();
    Mono<Customer> findById(String customerId);
    Mono<Customer> registerProduct(String customerId, String productId);
}
