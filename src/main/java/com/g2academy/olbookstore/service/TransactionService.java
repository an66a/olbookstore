package com.g2academy.olbookstore.service;

import com.g2academy.olbookstore.mapper.TransactionMapper;
import com.g2academy.olbookstore.model.Book;
import com.g2academy.olbookstore.model.Customer;
import com.g2academy.olbookstore.model.Transaction;
import com.g2academy.olbookstore.model.TransactionItem;
import com.g2academy.olbookstore.repository.BookRepo;
import com.g2academy.olbookstore.repository.CustomerRepo;
import com.g2academy.olbookstore.repository.TransactionItemRepo;
import com.g2academy.olbookstore.repository.TransactionRepo;
import com.g2academy.olbookstore.service.dto.TransactionDto;
import com.g2academy.olbookstore.service.dto.TransactionItemDto;
import com.g2academy.olbookstore.service.response.CustomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepo transactionRepo;

    private final TransactionItemRepo transactionItemRepo;

    private final CustomerRepo customerRepo;

    private final BookRepo bookRepo;

    TransactionService(TransactionRepo transactionRepo, TransactionItemRepo transactionItemRepo, CustomerRepo customerRepo, BookRepo bookRepo) {
        this.transactionItemRepo = transactionItemRepo;
        this.transactionRepo = transactionRepo;
        this.customerRepo = customerRepo;
        this.bookRepo = bookRepo;
    }

    private Transaction save(Transaction entity) {
        return this.transactionRepo.save(entity);
    }

    public ResponseEntity<List<TransactionDto>> findAll() {
        return ResponseEntity.ok(TransactionMapper.INSTANCE.toDtos(transactionRepo.findAll()));
    }

    public ResponseEntity<List<TransactionDto>> findByCutomerId(Long id) {
        return ResponseEntity.ok(TransactionMapper.INSTANCE.toDtosWithoutCustomerDetail(transactionRepo.findByCustomerId(id)));
    }

    public ResponseEntity<List<TransactionDto>> findByBookTitle(String title) {
        return ResponseEntity.ok(TransactionMapper.INSTANCE.toDtos(transactionRepo.findByTransactionItems_Book_Title(title)));
    }

    public ResponseEntity<List<TransactionDto>> findByBookIsbn(String isbn) {
        return ResponseEntity.ok(TransactionMapper.INSTANCE.toDtos(transactionRepo.findByTransactionItems_Book_Isbn(isbn)));
    }

    public ResponseEntity<?> findById(Long id) {
        Transaction transaction = transactionRepo.findById(id).orElse(null);
        if (transaction == null) return new CustomResponse().transactionNotFound();
        return ResponseEntity.ok(TransactionMapper.INSTANCE.toDto(transaction));
    }

    public ResponseEntity<?> addTransaction(TransactionDto transactionDto) {
        Customer customer = customerRepo.findById(transactionDto.getCustomerId()).orElse(null);
        if (customer == null) return new CustomResponse().customerNotFound();
        Transaction transaction = Transaction.builder()
                .customer(customer)
                .build();
        if (transactionDto.getTransactionItems() != null) {
            for (TransactionItemDto transItemDto : transactionDto.getTransactionItems()) {
                Book book = bookRepo.findById(transItemDto.getBookId()).orElse(null);
                if (book == null) return new CustomResponse().bookNotFound();
                TransactionItem transItemEntity = TransactionItem.builder()
                        .book(book)
                        .quantity(transItemDto.getQuantity())
                        .build();
                transaction.addTransactionItem(transItemEntity);
            }
        }
        return ResponseEntity.ok(TransactionMapper.INSTANCE.toDto(this.save(transaction)));
    }

    public ResponseEntity<?> addTransactionItem(Long transactionId, TransactionItemDto itemDto) {
        Transaction transaction = transactionRepo.findById(transactionId).orElse(null);
        if (transaction == null) return new CustomResponse().transactionNotFound();
        Book book = bookRepo.findById(itemDto.getBookId()).orElse(null);
        if (book == null) return new CustomResponse().bookNotFound();
        TransactionItem transactionItem = TransactionItem.builder()
                .book(book)
                .quantity(itemDto.getQuantity())
                .build();
        transaction.addTransactionItem(transactionItem);
        return ResponseEntity.ok(TransactionMapper.INSTANCE.toDto(this.save(transaction)));
    }

    public ResponseEntity<?> updateTransactionItem(Long transId, TransactionItemDto itemDto) {
        Transaction transaction = transactionRepo.findById(transId).orElse(null);
        if (transaction == null) return new CustomResponse().transactionNotFound();

        TransactionItem itemEntity = transaction.getTransactionItemById(itemDto.getId());
        if (itemEntity == null) return new CustomResponse().transactionItemNotFound();

        Book book = bookRepo.findById(itemDto.getBookId()).orElse(null);
        if (book == null) return new CustomResponse().bookNotFound();

        itemEntity.setBook(book);
        itemEntity.setQuantity(itemDto.getQuantity());

        return ResponseEntity.ok(TransactionMapper.INSTANCE.toDto(transactionRepo.save(transaction)));
    }

    public void deleteTransaction(Long transId) {
        transactionRepo.deleteById(transId);
    }

    public void deleteTransactionItem(Long itemId) {
        transactionItemRepo.deleteById(itemId);
    }

}
