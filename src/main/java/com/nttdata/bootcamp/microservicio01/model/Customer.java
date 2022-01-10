package com.nttdata.bootcamp.microservicio01.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Document(collection = "customer")
public class Customer {

    @Id
    private String id = UUID.randomUUID().toString();
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String typeDocument;
    private String document;
    private String typeClient;
    private Date createdAt;
    private Boolean owner;
    private List<Product> product = new ArrayList<>();;

    public void addProduct(Product p) {
        this.product.add(p);
    }
}