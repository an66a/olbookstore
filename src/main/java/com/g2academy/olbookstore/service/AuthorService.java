package com.g2academy.olbookstore.service;

import com.g2academy.olbookstore.mapper.AuthorMapper;
import com.g2academy.olbookstore.model.Author;
import com.g2academy.olbookstore.model.Book;
import com.g2academy.olbookstore.model.Publisher;
import com.g2academy.olbookstore.repository.AuthorRepo;
import com.g2academy.olbookstore.repository.PublisherRepo;
import com.g2academy.olbookstore.service.dto.AuthorDto;
import com.g2academy.olbookstore.service.dto.BookDto;
import com.g2academy.olbookstore.service.response.CustomResponse;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

   private final AuthorRepo authorRepo;
   private final PublisherRepo publisherRepo;

   AuthorService(AuthorRepo authorRepo, PublisherRepo publisherRepo){
       this.authorRepo = authorRepo;
       this.publisherRepo = publisherRepo;
   }

    private Author save(Author entity) {
        return this.authorRepo.save(entity);
    }

    public ResponseEntity<List<AuthorDto>> findAll() {
        return ResponseEntity.ok(AuthorMapper.INSTANCE.toDtos(authorRepo.findAll(Sort.by(Sort.Direction.ASC, "id"))));
    }

    public ResponseEntity<?> findById(Long id){
       Author author = authorRepo.findById(id).orElse(null);
       if (author == null) return new CustomResponse().authorNotFound();
        return ResponseEntity.ok(AuthorMapper.INSTANCE.toDto(author));
    }

    public ResponseEntity<?> add(AuthorDto authorDto) {
        Author authorEntity = Author.builder()
                .firstName(authorDto.getFirstName())
                .lastName(authorDto.getLastName())
                .email(authorDto.getEmail())
                .address(authorDto.getAddress())
                .url(authorDto.getUrl())
                .build();
        if (authorDto.getBooks() != null) {
            for (BookDto bookDto : authorDto.getBooks()) {
                Book bookEntity = Book.builder()
                        .isbn(bookDto.getIsbn())
                        .publishedOn(bookDto.getPublishedOn())
                        .title(bookDto.getTitle())
                        .price(bookDto.getPrice())
                        .build();
                Publisher publisher = publisherRepo.findById(bookDto.getPublisherId()).orElse(null);
                if (publisher == null) return new CustomResponse().publisherNotFound();
                bookEntity.setPublisher(publisher);
                authorEntity.addBook(bookEntity);
            }
        }
        return ResponseEntity.ok(AuthorMapper.INSTANCE.toDto(this.save(authorEntity)));
    }

    public ResponseEntity<?> update(Long id, AuthorDto authorDto) {
        Author author = authorRepo.findById(id).orElse(null);
        if (author == null) return new CustomResponse().authorNotFound();
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        author.setEmail(authorDto.getEmail());
        author.setAddress(authorDto.getAddress());
        author.setUrl(authorDto.getUrl());
        return ResponseEntity.ok(AuthorMapper.INSTANCE.toDto(this.save(author)));
    }

    public void delete(Long id) {
        authorRepo.deleteById(id);
    }

    public ResponseEntity<?> addBook(Long id, BookDto bookDto) {
        Author author = authorRepo.findById(id).orElse(null);
        if (author == null) return new CustomResponse().authorNotFound();
        Publisher publisher = publisherRepo.findById(bookDto.getPublisherId()).orElse(null);
        if (publisher == null) return new CustomResponse().publisherNotFound();
        Book book = Book.builder()
                .isbn(bookDto.getIsbn())
                .publishedOn(bookDto.getPublishedOn())
                .title(bookDto.getTitle())
                .price(bookDto.getPrice())
                .publisher(publisher)
                .build();
        author.addBook(book);
        return ResponseEntity.ok(AuthorMapper.INSTANCE.toDto(this.save(author)));
    }
}
