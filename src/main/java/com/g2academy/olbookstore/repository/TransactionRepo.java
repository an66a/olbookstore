package com.g2academy.olbookstore.repository;

import com.g2academy.olbookstore.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    @Query("FROM Transaction WHERE customer_id = ?1")
    List<Transaction> findByCustomerId(Long id);

    List<Transaction> findByTransactionItems_Book_Title(String title);
    List<Transaction> findByTransactionItems_Book_Isbn(String isbn);
}
