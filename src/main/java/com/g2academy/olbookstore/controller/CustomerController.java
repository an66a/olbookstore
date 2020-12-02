package com.g2academy.olbookstore.controller;

import com.g2academy.olbookstore.service.CustomerService;
import com.g2academy.olbookstore.service.TransactionService;
import com.g2academy.olbookstore.service.dto.CustomerDto;
import com.g2academy.olbookstore.service.dto.TransactionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

   private final CustomerService customerService;
   private final TransactionService transactionService;

    public CustomerController(CustomerService customerService, TransactionService transactionService) {
        this.customerService = customerService;
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> all(){
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id){
        return customerService.findById(id);
    }

    @GetMapping(params = "name")
    public ResponseEntity<List<CustomerDto>> findByName(@RequestParam String name){
        return customerService.findByName(name);
    }

    @GetMapping(params = "email")
    public ResponseEntity<?> findByEmail(@RequestParam String email){
        return customerService.findByEmail(email);
    }

    @GetMapping("/{id}/transactions")
    private ResponseEntity<List<TransactionDto>> getShoppingBasket(@PathVariable("id") Long id){
        return transactionService.findByCutomerId(id);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid CustomerDto customerDto){
        return customerService.add(customerDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody @Valid CustomerDto customerDto){
        return customerService.update(id, customerDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        customerService.delete(id);
    }
}
