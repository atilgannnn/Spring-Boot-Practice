package com.tpe.controller;

import com.tpe.domain.Customer;
import com.tpe.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //1-Spring Bootu selamlama:)->http://localhost:8080/customers/greet
    @GetMapping("/greet")
    public String greetSpringBoot(){
        return "Hello Spring Boot:)";
    }

    //2-customer oluşturma/kaydetme->http://localhost:8080/customers/save + JSON BODY
    @PostMapping("/save")
    public ResponseEntity<String> saveCustomer(@Valid @RequestBody Customer customer){
        customerService.saveCustomer(customer);
        String message="Customer is saved successfully";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    //3-Tüm customerları getirme->http://localhost:8080/customers
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> customerList=customerService.getAllCustomer();
        return ResponseEntity.ok(customerList);//200:OK
    }
    //4-Id ile tek bir customer getirme->http://localhost:8080/customers/1
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id){
        Customer customer=customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    //ÖDEV-Id ile tek bir customer getirme->http://localhost:8080/customers/custom?id=1

    //ÖDEV2-Id ile bir customerı silme->http://localhost:8080/customers/custom?id=1
    //Customer is deleted successfully mesajı dönsün.





}
