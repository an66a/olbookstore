package com.g2academy.olbookstore.controller;

import com.g2academy.olbookstore.service.BookService;
import com.g2academy.olbookstore.service.dto.BookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

   private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> all() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) {
        return bookService.findById(id);
    }

    @GetMapping(params = "authorId")
    public ResponseEntity<List<BookDto>> findByAuthorId(@RequestParam Long authorId) {
        return bookService.findByAuthorId(authorId);
    }

    @GetMapping(params = "publisherId")
    public ResponseEntity<List<BookDto>> findByPublisherId(@RequestParam Long publisherId) {
        return bookService.findByPublisherId(publisherId);
    }

    @GetMapping(params = "isbn")
    public ResponseEntity<?> findByIsbn(@RequestParam String isbn) {
        return bookService.findByIsbn(isbn);
    }

    @GetMapping(params = "title")
    public ResponseEntity<?> findByTitle(@RequestParam String title) {
        return bookService.findByTitle(title);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid BookDto bookDto) {
        return bookService.add(bookDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody @Valid BookDto bookDto) {
        return bookService.update(id, bookDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        bookService.delete(id);
    }
}
