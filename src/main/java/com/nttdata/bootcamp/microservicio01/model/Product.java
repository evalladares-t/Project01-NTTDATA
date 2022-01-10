package com.nttdata.bootcamp.microservicio01.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Document(collection = "product")
public class Product {

    @Id
    private String id = UUID.randomUUID().toString();
    private String typeProduct;
    private String name;
    private Float commission;
    private Integer maxMovements;
    private Integer dateAction;
    private Boolean active;
    private Integer totalAmount;
    private Integer totalCredit;



}
