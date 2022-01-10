package com.nttdata.bootcamp.microservicio01.expose;

import com.nttdata.bootcamp.microservicio01.bussiness.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.nttdata.bootcamp.microservicio01.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping(path = "${v1API}/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public Flux<Product> findAll(@RequestParam(value = "type_product",defaultValue = "") String type_product) {
        log.info("findAll>>>>>");
        return type_product.isEmpty()?productService.findAll():productService.findBytype(type_product);
    }

    @GetMapping("/{id}")
    public Mono<Product> findbyId(@PathVariable("id") String id) {
        log.info("byId>>>>>");
        return productService.findByIdProduct(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<Mono<Product>>> create(@RequestBody Product product) {
        log.info("create>>>>>");
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.create(product)));
    }

    @PutMapping("")
    public Mono<ResponseEntity<Product>> update(@RequestBody Product product) {
        log.info("update>>>>>");
        return productService.update(product)
                .flatMap(productUpdate -> Mono.just(ResponseEntity.ok(productUpdate)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

}
