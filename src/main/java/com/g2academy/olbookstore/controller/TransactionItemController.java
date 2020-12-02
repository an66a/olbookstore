package com.g2academy.olbookstore.controller;

import com.g2academy.olbookstore.service.TransactionItemService;
import com.g2academy.olbookstore.service.dto.TransactionItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transaction-items")
public class TransactionItemController {

   private final TransactionItemService itemService;

    public TransactionItemController(TransactionItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionItemDto>> all(){
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return itemService.findById(id);
    }


    @GetMapping(params = "bookId")
    public ResponseEntity<List<TransactionItemDto>> findByBookId(@RequestParam Long bookId){
        return itemService.findByBookId(bookId);
    }

    @GetMapping(params = "isbn")
    public ResponseEntity<List<TransactionItemDto>> findByBookIsbn(@RequestParam String isbn){
        return itemService.findByBookIsbn(isbn);
    }

    @GetMapping(params = "title")
    public ResponseEntity<List<TransactionItemDto>> findByBookTitle(@RequestParam String title){
        return itemService.findByBookTitle(title);
    }

    @GetMapping(params = "authorId")
    public ResponseEntity<List<TransactionItemDto>> findByBookAuthorId(@RequestParam Long authorId){
        return itemService.findByBookAuthorId(authorId);
    }

    @GetMapping(params = "publisherId")
    public ResponseEntity<List<TransactionItemDto>> findByBookPublisherId(@RequestParam Long publisherId){
        return itemService.findByBookPublisherId(publisherId);
    }

    @PostMapping
    public ResponseEntity<?> addTransItem(@RequestBody @Valid TransactionItemDto itemDto){
        return itemService.add(itemDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable("id") Long id, @RequestBody @Valid TransactionItemDto itemDto){
        return itemService.update(id, itemDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
         itemService.delete(id);
    }
}
