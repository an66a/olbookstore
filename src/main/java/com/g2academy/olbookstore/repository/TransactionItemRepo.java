package com.g2academy.olbookstore.repository;

import com.g2academy.olbookstore.model.TransactionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionItemRepo extends JpaRepository<TransactionItem, Long> {

    List<TransactionItem> findByBook_Id(Long bookid);

    List<TransactionItem> findByBook_Isbn(String isbn);

    List<TransactionItem> findByBook_Title(String title);

    List<TransactionItem> findByBook_Author_Id(Long authorId);

    List<TransactionItem> findByBook_Publisher_Id(Long publisherId);

}
