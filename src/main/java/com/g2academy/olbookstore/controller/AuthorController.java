package com.g2academy.olbookstore.controller;

import com.g2academy.olbookstore.service.AuthorService;
import com.g2academy.olbookstore.service.dto.AuthorDto;
import com.g2academy.olbookstore.service.dto.BookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> all(){
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id){
        return authorService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid AuthorDto authorDto){
        return authorService.add(authorDto);
    }

    @PostMapping("/{id}/books")
    public ResponseEntity<?> addBook(@PathVariable("id") Long id, @RequestBody @Valid BookDto bookDto){
        return authorService.addBook(id, bookDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody @Valid AuthorDto authorDto ){
        return authorService.update(id, authorDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        authorService.delete(id);
    }

}
