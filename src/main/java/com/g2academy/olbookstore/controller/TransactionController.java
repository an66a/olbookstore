package com.g2academy.olbookstore.controller;

import com.g2academy.olbookstore.service.TransactionService;
import com.g2academy.olbookstore.service.dto.TransactionDto;
import com.g2academy.olbookstore.service.dto.TransactionItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> all() {
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long basketId) {
        return transactionService.findById(basketId);
    }

    @GetMapping(params = "customerId")
    public ResponseEntity<List<TransactionDto>> findByCustomerId(@RequestParam Long customerId) {
        return transactionService.findByCutomerId(customerId);
    }

    @GetMapping(params = "bookTitle")
    public ResponseEntity<List<TransactionDto>> findByBookTitle(@RequestParam String bookTitle) {
        return transactionService.findByBookTitle(bookTitle);
    }

    @GetMapping(params = "bookIsbn")
    public ResponseEntity<List<TransactionDto>> findByBookIsbn(@RequestParam String isbn) {
        return transactionService.findByBookIsbn(isbn);
    }

    @PostMapping
    public ResponseEntity<?> addTransaction(@RequestBody @Valid TransactionDto transaction) {
        return transactionService.addTransaction(transaction);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> addTransactionItem(@PathVariable("id") Long transId, @RequestBody @Valid TransactionItemDto itemDto) {
        return transactionService.addTransactionItem(transId, itemDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTransactionItem(@PathVariable("id") Long transId, @RequestBody @Valid TransactionItemDto itemDto) {
        return transactionService.updateTransactionItem(transId, itemDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable("id") Long transId) {
        transactionService.deleteTransaction(transId);
    }

    @DeleteMapping(value = "/{id}", params = "itemId")
    public void deleteTransactionItem(@RequestParam Long itemId) {
        transactionService.deleteTransactionItem(itemId);
    }

}
