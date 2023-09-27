package com.cashback.service.customer;

import com.cashback.model.customer.Customer;
import com.cashback.repository.customer.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;
    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findByid(String string) {
        return this.customerRepository.findById(Integer.parseInt(string));
    }

    @Override
    public Customer save(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public void delete(String id) {
         this.customerRepository.deleteById(Integer.parseInt(id));
    }
}