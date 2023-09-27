package com.cashback.resource.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface ICustomerResource<T> {

    ResponseEntity<?> findById(String t);
    ResponseEntity<?> findAll();
    ResponseEntity<?> create(T t);

    ResponseEntity<?> update(T t);
    ResponseEntity<?> delete(String t);
}
