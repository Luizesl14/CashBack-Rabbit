package com.cashback.resource.customer;

import com.cashback.model.customer.Customer;
import com.cashback.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/customer")
public class CustomerResource implements ICustomerResource<Customer> {

    @Autowired
    private ICustomerService customerService;

    @GetMapping(value = "/{id}", produces="application/json")
    @Override
    public ResponseEntity<?> findById(String t) {
        return ResponseEntity.ok(this.customerService.findByid(t));
    }

    @GetMapping(value = "/all", produces="application/json")
    @Override
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.customerService.findAll());
    }

    @PostMapping(value = "/create")
    @Override
    public ResponseEntity<?> create(@RequestBody Customer customer) {
        return ResponseEntity.ok(this.customerService.save(customer));
    }

    @PutMapping(value = "/update", produces="application/json")
    @Override
    public ResponseEntity<?> update(@RequestBody Customer customer) {
        return ResponseEntity.ok(this.customerService.update(customer));
    }


    @GetMapping(value = "/delete", produces="application/json")
    @Override
    public ResponseEntity<?> delete(String t) {
        this.customerService.delete(t);
        return ResponseEntity.ok().build();
    }
}
