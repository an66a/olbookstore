package com.g2academy.olbookstore.service;

import com.g2academy.olbookstore.mapper.CustomerMapper;
import com.g2academy.olbookstore.model.Customer;
import com.g2academy.olbookstore.repository.CustomerRepo;
import com.g2academy.olbookstore.service.dto.CustomerDto;
import com.g2academy.olbookstore.service.response.CustomResponse;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepo customerRepo;

    CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    private Customer save(Customer entity) {
        return this.customerRepo.save(entity);
    }

    public ResponseEntity<List<CustomerDto>> findAll() {
        return ResponseEntity.ok(CustomerMapper.INSTANCE.toDtos(customerRepo.findAll(Sort.by(Sort.Direction.ASC, "id"))));
    }

    public ResponseEntity<?> findById(Long id) {
        Customer customer = findCustomerById(id);
        if (customer == null) return new CustomResponse().customerNotFound();
        return ResponseEntity.ok(CustomerMapper.INSTANCE.toDto(customer));
    }

    public ResponseEntity<List<CustomerDto>>  findByName(String name) {
        return ResponseEntity.ok(CustomerMapper.INSTANCE.toDtos(customerRepo.findByName(name)));
    }

    public ResponseEntity<?> findByEmail(String email) {
        Customer customer = customerRepo.findByEmail(email).orElse(null);
        if (customer == null) return new CustomResponse().customerNotFound();
        return ResponseEntity.ok(CustomerMapper.INSTANCE.toDto(customer));
    }

    public ResponseEntity<?> add(CustomerDto customerDto) {
       Customer customer = customerRepo.findByEmail(customerDto.getEmail()).orElse(null);
       if (customer != null) return new CustomResponse().emailAlreadyExist();
        Customer customerEntity = Customer.builder()
                .email(customerDto.getEmail())
                .name(customerDto.getName())
                .phone(customerDto.getPhone())
                .address(customerDto.getAddress())
                .build();
        return ResponseEntity.ok(CustomerMapper.INSTANCE.toDto(this.save(customerEntity)));
    }

    public ResponseEntity<?> update(Long id, CustomerDto customerDto) {
        Customer customer = findCustomerById(id);
        if (customer == null) return new CustomResponse().customerNotFound();
        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        customer.setPhone(customerDto.getPhone());
        customer.setAddress(customerDto.getAddress());
        return ResponseEntity.ok(CustomerMapper.INSTANCE.toDto(this.save(customer)));
    }

    public void delete(Long id) {
        customerRepo.deleteById(id);
    }

    private Customer findCustomerById(Long id) {
        return this.customerRepo.findById(id).orElse(null);
    }
}

