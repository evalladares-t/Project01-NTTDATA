package com.nttdata.bootcamp.microservicio01.bussiness.impl;
import com.nttdata.bootcamp.microservicio01.bussiness.CustomerService;
import com.nttdata.bootcamp.microservicio01.model.Customer;
import com.nttdata.bootcamp.microservicio01.model.Product;
import com.nttdata.bootcamp.microservicio01.repository.CustomerRepository;
import com.nttdata.bootcamp.microservicio01.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Date;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Mono<Customer> create(Customer customer) {
        Mono<Customer> customerMono = customerRepository.findCustomerByTypeDocumentAndDocument(customer.getTypeDocument(), customer.getDocument());
        return customerMono.flatMap(p->{
            if(p.getTypeClient().equals("empresarial")){
                customer.setOwner(false);
                customer.setCreatedAt(new Date());
                return customerRepository.save(customer);
            }
            return customerRepository.findCustomerByTypeDocumentAndDocument(customer.getTypeDocument(), customer.getDocument());
        }).switchIfEmpty(Mono.defer(() -> {
                customer.setCreatedAt(new Date());
                customer.setOwner(true);
                return customerRepository.save(customer);}));
    }

    @Override
    public Flux<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Mono<Customer> findById(String customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public Mono<Customer> registerProduct(String customerId, String productId) {
        Mono<Product> objProduct = productRepository.findById(productId);

        return findById(customerId).flatMap(c->{
            return objProduct.flatMap(p->{
                if (c.getTypeClient().equals("personal")) {
                    if(p.getTypeProduct().equals("pasivo") && c.getProduct().stream()
                            .filter(l->l.getName().equals(p.getName())).filter(l->l.getActive()).count() ==0){
                        c.addProduct(p);
                    }
                    if(p.getTypeProduct().equals("activo") && c.getProduct().stream()
                            .filter(l->l.getTypeProduct().equals("activo")).filter(l->l.getActive()).count() ==0){
                        c.addProduct(p);
                    }
                }
                if (c.getTypeClient().equals("empresarial")) {
                    if(p.getTypeProduct().equals("pasivo") && p.getName().equals("Cuenta Corriente")){
                        c.addProduct(p);
                    }
                    if(p.getTypeProduct().equals("activo")){
                        c.addProduct(p);
                    }
                }
                customerRepository.save(c).subscribe();
                return findById(c.getId());
            });
        });
    }
}
