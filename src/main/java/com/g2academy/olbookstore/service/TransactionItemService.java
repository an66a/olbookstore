package com.g2academy.olbookstore.service;

import com.g2academy.olbookstore.mapper.TransactionItemMapper;
import com.g2academy.olbookstore.model.Book;
import com.g2academy.olbookstore.model.Transaction;
import com.g2academy.olbookstore.model.TransactionItem;
import com.g2academy.olbookstore.repository.BookRepo;
import com.g2academy.olbookstore.repository.TransactionItemRepo;
import com.g2academy.olbookstore.repository.TransactionRepo;
import com.g2academy.olbookstore.service.dto.TransactionItemDto;
import com.g2academy.olbookstore.service.response.CustomResponse;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionItemService {

    private final TransactionRepo transactionRepo;
    private final TransactionItemRepo transactionItemRepo;
    private final BookRepo bookRepo;

    TransactionItemService(TransactionItemRepo transactionItemRepo, TransactionRepo transactionRepo, BookRepo bookRepo) {
        this.transactionItemRepo = transactionItemRepo;
        this.transactionRepo = transactionRepo;
        this.bookRepo = bookRepo;
    }

    private TransactionItem save(TransactionItem entity) {
        return this.transactionItemRepo.save(entity);
    }

    public ResponseEntity<List<TransactionItemDto>> findAll() {
        return ResponseEntity.ok(TransactionItemMapper.INSTANCE.toDtos(transactionItemRepo.findAll(Sort.by(Sort.Direction.ASC, "id"))));
    }

    public ResponseEntity<?> findById(Long id) {
        TransactionItem item = transactionItemRepo.findById(id).orElse(null);
        if (item == null) return new CustomResponse().transactionItemNotFound();
        return ResponseEntity.ok(TransactionItemMapper.INSTANCE.toDto(item));
    }

    public ResponseEntity<List<TransactionItemDto>> findByBookId(Long id) {
        return ResponseEntity.ok(TransactionItemMapper.INSTANCE.toDtos(transactionItemRepo.findByBook_Id(id)));
    }

    public ResponseEntity<List<TransactionItemDto>> findByBookIsbn(String isbn) {
        return ResponseEntity.ok(TransactionItemMapper.INSTANCE.toDtos(transactionItemRepo.findByBook_Isbn(isbn)));
    }

    public ResponseEntity<List<TransactionItemDto>>  findByBookTitle(String title) {
        return ResponseEntity.ok(TransactionItemMapper.INSTANCE.toDtos(transactionItemRepo.findByBook_Title(title)));
    }

    public ResponseEntity<List<TransactionItemDto>>  findByBookAuthorId(Long id) {
        return ResponseEntity.ok(TransactionItemMapper.INSTANCE.toDtos(transactionItemRepo.findByBook_Author_Id(id)));
    }

    public ResponseEntity<List<TransactionItemDto>>  findByBookPublisherId(Long id) {
      return   ResponseEntity.ok(TransactionItemMapper.INSTANCE.toDtos(transactionItemRepo.findByBook_Publisher_Id(id)));
    }

    public ResponseEntity<?> add(TransactionItemDto itemDto) {
        Transaction transaction = transactionRepo.findById(itemDto.getTransactionId()).orElse(null);
        if (transaction == null) return new CustomResponse().transactionNotFound();
        Book book = bookRepo.findById(itemDto.getBookId()).orElse(null);
        if (book == null) return new CustomResponse().bookNotFound();
        TransactionItem itemEntity = TransactionItem.builder()
                .transaction(transaction)
                .book(book)
                .quantity(itemDto.getQuantity())
                .build();
        return ResponseEntity.ok(TransactionItemMapper.INSTANCE.toDto(this.save(itemEntity)));
    }

    public ResponseEntity<?> update(Long id, TransactionItemDto itemDto) {
        TransactionItem itemEntity = transactionItemRepo.findById(id).orElse(null);
        if (itemEntity == null) return new CustomResponse().transactionItemNotFound();
        Book book = bookRepo.findById(itemDto.getBookId()).orElse(null);
        if (book == null) return new CustomResponse().bookNotFound();
        itemEntity.setBook(book);
        itemEntity.setQuantity(itemDto.getQuantity());
        return ResponseEntity.ok(TransactionItemMapper.INSTANCE.toDto(this.save(itemEntity)));
    }

    public void delete(Long id) {
        transactionItemRepo.deleteById(id);
    }
}
