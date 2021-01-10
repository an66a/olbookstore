package com.g2academy.olbookstore.service;

import com.g2academy.olbookstore.mapper.PublisherMapper;
import com.g2academy.olbookstore.model.Author;
import com.g2academy.olbookstore.model.Book;
import com.g2academy.olbookstore.model.Publisher;
import com.g2academy.olbookstore.repository.AuthorRepo;
import com.g2academy.olbookstore.repository.PublisherRepo;
import com.g2academy.olbookstore.service.dto.BookDto;
import com.g2academy.olbookstore.service.dto.PublisherDto;
import com.g2academy.olbookstore.service.response.CustomResponse;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    private final PublisherRepo publisherRepo;

    private  final AuthorRepo authorRepo;

    PublisherService(PublisherRepo publisherRepo, AuthorRepo authorRepo){
        this.publisherRepo = publisherRepo;
        this.authorRepo = authorRepo;
    }

    private Publisher save(Publisher entity) {
        return this.publisherRepo.save(entity);
    }

    public ResponseEntity<List<PublisherDto>> findAll(){
        return ResponseEntity.ok(PublisherMapper.INSTANCE.toDtos(publisherRepo.findAll(Sort.by(Sort.Direction.ASC, "id"))));
    }

    public ResponseEntity<?>  findById(Long id) {
        Publisher publisher = publisherRepo.findById(id).orElse(null);
        if (publisher == null) return new CustomResponse().publisherNotFound();
        return ResponseEntity.ok(PublisherMapper.INSTANCE.toDto(publisher));
    }

    public ResponseEntity<?> add(PublisherDto publisherDto) {
        Publisher publisher = Publisher.builder()
                .name(publisherDto.getName())
                .address(publisherDto.getAddress())
                .phone(publisherDto.getPhone())
                .url(publisherDto.getUrl())
                .build();
        if (publisherDto.getBooks() != null) {
            for (BookDto bookDto : publisherDto.getBooks()) {
                Book bookEntity = Book.builder()
                        .isbn(bookDto.getIsbn())
                        .publishedOn(bookDto.getPublishedOn())
                        .title(bookDto.getTitle())
                        .price(bookDto.getPrice())
                        .build();
                Author author = authorRepo.findById(bookDto.getAuthorId()).orElse(null);
                if (author == null) return new CustomResponse().authorNotFound();
                bookEntity.setAuthor(author);
                publisher.addBook(bookEntity);
            }
        }
        return ResponseEntity.ok(PublisherMapper.INSTANCE.toDto(this.save(publisher)));
    }

    public ResponseEntity<?> update(Long id, PublisherDto publisherDto) {
        Publisher publisher = publisherRepo.findById(id).orElse(null);
        if (publisher == null) return new CustomResponse().publisherNotFound();
        publisher.setName(publisherDto.getName());
        publisher.setAddress(publisherDto.getAddress());
        publisher.setPhone(publisherDto.getPhone());
        publisher.setUrl(publisherDto.getUrl());
        return ResponseEntity.ok(PublisherMapper.INSTANCE.toDto(this.save(publisher)));
    }
    public void delete(Long id) {
        publisherRepo.deleteById(id);
    }
}
