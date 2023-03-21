package com.tpe.service;

import com.tpe.domain.Customer;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public void saveCustomer(Customer customer) {
        boolean isExistCustomer=customerRepository.existsByEmail(customer.getEmail());
        if(isExistCustomer){
            throw new ConflictException("Customer already exists by email"+customer.getEmail());
        }
        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomer(){
       List<Customer> customers=customerRepository.findAll();
       return customers;
    }


    public Customer getCustomerById(Long id) {
        Customer customer=customerRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Customer not found by id: "+id));
        return customer;
    }
}
