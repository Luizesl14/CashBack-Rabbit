package com.cashback.resource.messager;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IMessageResource<T> {

    ResponseEntity<?> findById(String t);
    ResponseEntity<?> findAll();
    ResponseEntity<?> create() throws Exception;
    ResponseEntity<?> delete();
}
