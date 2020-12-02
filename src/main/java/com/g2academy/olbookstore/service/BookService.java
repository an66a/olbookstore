package com.g2academy.olbookstore.service;

import com.g2academy.olbookstore.mapper.BookMapper;
import com.g2academy.olbookstore.model.Author;
import com.g2academy.olbookstore.model.Book;
import com.g2academy.olbookstore.model.Publisher;
import com.g2academy.olbookstore.repository.AuthorRepo;
import com.g2academy.olbookstore.repository.BookRepo;
import com.g2academy.olbookstore.repository.PublisherRepo;
import com.g2academy.olbookstore.service.dto.BookDto;
import com.g2academy.olbookstore.service.response.CustomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepo bookRepo;

    private final AuthorRepo authorRepo;

    private final PublisherRepo publisherRepo;

    BookService(BookRepo bookRepo, AuthorRepo authorRepo, PublisherRepo publisherRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
        this.publisherRepo = publisherRepo;
    }

    private Book save(Book entity) {
        return this.bookRepo.save(entity);
    }

//    private Function<List<Book>, List<BookDto>> toDtos() {
//        return (x) -> BookMapper.INSTANCE.toDtos(x);
//    }
//
//    private Function<List<Book>, ResponseEntity<List<BookDto>>> getAll() {
//        return (x) -> new ResponseEntity<List<BookDto>>(this.toDtos().apply(x), HttpStatus.OK);
//    }

    public ResponseEntity<List<BookDto>> findAll() {
        return ResponseEntity.ok(BookMapper.INSTANCE.toDtos(bookRepo.findAll()));
    }

    public ResponseEntity<?> findById(Long id) {
        Book book = bookRepo.findById(id).orElse(null);
        if (book == null) return new CustomResponse().bookNotFound();
        return ResponseEntity.ok(BookMapper.INSTANCE.toDto(book));
    }

    public ResponseEntity<List<BookDto>> findByAuthorId(Long authorId) {
        return ResponseEntity.ok(BookMapper.INSTANCE.toDtos(bookRepo.findByAuthorId(authorId)));
    }

    public ResponseEntity<List<BookDto>> findByPublisherId(Long authorId) {
        return ResponseEntity.ok(BookMapper.INSTANCE.toDtos(bookRepo.findByPublisherId(authorId)));
    }

    public ResponseEntity<BookDto> findByIsbn(String isbn) {
        return ResponseEntity.ok(BookMapper.INSTANCE.toDto(bookRepo.findByIsbn(isbn)));
    }

    public ResponseEntity<?> findByTitle(String title) {
        return ResponseEntity.ok(BookMapper.INSTANCE.toDto(bookRepo.findByTitle(title)));
    }

    public ResponseEntity<?> add(BookDto bookDto) {
        Publisher publisher = publisherRepo.findById(bookDto.getPublisherId()).orElse(null);
        if (publisher == null) return new CustomResponse().publisherNotFound();
        Author author = authorRepo.findById(bookDto.getAuthorId()).orElse(null);
        if (author == null) return new CustomResponse().authorNotFound();
        Book bookEntity = Book.builder()
                .isbn(bookDto.getIsbn())
                .publishedOn(bookDto.getPublishedOn())
                .title(bookDto.getTitle())
                .price(bookDto.getPrice())
                .publisher(publisher)
                .author(author)
                .build();
        return ResponseEntity.ok(BookMapper.INSTANCE.toDto(this.save(bookEntity)));
    }

    public ResponseEntity<?> update(Long id, BookDto bookDto) {
        Book book = bookRepo.findById(id).orElse(null);
        if (book == null) return new CustomResponse().bookNotFound();
        book.setIsbn(bookDto.getIsbn());
        book.setPublishedOn(bookDto.getPublishedOn());
        book.setTitle(bookDto.getTitle());
        book.setPrice(bookDto.getPrice());

        if (bookDto.getAuthorId() != null) authorRepo.findById(bookDto.getAuthorId()).ifPresent(book::setAuthor);
        if (bookDto.getPublisherId() != null) publisherRepo.findById(bookDto.getPublisherId()).ifPresent(book::setPublisher);

        return ResponseEntity.ok(BookMapper.INSTANCE.toDto(this.save(book)));
    }

    public void delete(Long id) {
        bookRepo.deleteById(id);
    }
}
