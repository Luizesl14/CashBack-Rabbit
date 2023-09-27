package com.cashback.service;

import com.cashback.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ICustomerService {

    List<Customer> findAll();
    Optional<Customer> findByid(String string);
    Customer save(Customer customer);
    Customer update(Customer customer);
    void delete(String id);
}
