package com.nttdata.bootcamp.microservicio01.expose;


import com.nttdata.bootcamp.microservicio01.bussiness.CustomerService;
import com.nttdata.bootcamp.microservicio01.bussiness.ProductService;
import com.nttdata.bootcamp.microservicio01.model.Customer;
import com.nttdata.bootcamp.microservicio01.model.dto.RegisterproductoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping(path ="${v1API}/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public Mono<ResponseEntity<Flux<Customer>>> findAll() {
        log.info("findAll>>>>>");
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(customerService.findAll()));
    }

    @GetMapping("/{id}")
    public Mono<Customer> findbyId(@PathVariable("id") String id) {
        log.info("byId>>>>>");
        return customerService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<Mono<Customer>>> create(@RequestBody Customer customer){
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(customerService.create(customer)));
    }

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<Mono<Customer>>> registerProduct(@RequestBody RegisterproductoDTO objRegister){
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(customerService.registerProduct(objRegister.getCustomerId(),objRegister.getProductId())));
    }
}
