package com.g2academy.olbookstore.controller;

import com.g2academy.olbookstore.service.WarehouseBookService;
import com.g2academy.olbookstore.service.dto.WarehouseBookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/wh-books")
public class WarehouseBookController {

    private final WarehouseBookService whBookService;

    public WarehouseBookController(WarehouseBookService whBookService) {
        this.whBookService = whBookService;
    }

    @GetMapping
    public ResponseEntity<List<WarehouseBookDto>> all() {
        return whBookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long warehouseId) {
        return whBookService.findById(warehouseId);
    }

    @GetMapping(params = "warehouseId")
    public ResponseEntity<List<WarehouseBookDto>> findByWarehouseId(@RequestParam Long warehouseId) {
        return whBookService.findByWarehouseId(warehouseId);
    }

    @GetMapping(params = "title")
    public ResponseEntity<List<WarehouseBookDto>> findByBookTitle(@RequestParam String title) {
        return whBookService.findByBookTitle(title);
    }

    @GetMapping(params = "isbn")
    public ResponseEntity<List<WarehouseBookDto>> findByBookIsbn(@RequestParam String isbn) {
        return whBookService.findByBookIsbn(isbn);
    }

    @GetMapping(params = "authorId")
    public ResponseEntity<List<WarehouseBookDto>> findByBookIsbn(@RequestParam Long authorId) {
        return whBookService.findByBookAuthorId(authorId);
    }

    @GetMapping(params = "publisherId")
    public ResponseEntity<List<WarehouseBookDto>> findByBookPublisherId(@RequestParam Long publisherId) {
        return whBookService.findByBookPublisherId(publisherId);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid WarehouseBookDto whBookDto) {
        return whBookService.add(whBookDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody @Valid WarehouseBookDto whBookDto) {
        return whBookService.update(id, whBookDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        whBookService.delete(id);
    }
}
