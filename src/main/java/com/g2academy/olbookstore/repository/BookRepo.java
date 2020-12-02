package com.g2academy.olbookstore.repository;

import com.g2academy.olbookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    List<Book> findByAuthorId(Long id);
    List<Book> findByPublisherId(Long id);
    Book findByIsbn(String isbn);
    Book findByTitle(String title);
}
