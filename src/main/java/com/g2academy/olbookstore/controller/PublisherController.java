package com.g2academy.olbookstore.controller;

import com.g2academy.olbookstore.service.PublisherService;
import com.g2academy.olbookstore.service.dto.PublisherDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public ResponseEntity<List<PublisherDto> >all() {
        return publisherService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id){
        return publisherService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid PublisherDto publisherDto) {
        return publisherService.add(publisherDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody @Valid PublisherDto publisherDto){
        return publisherService.update(id, publisherDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        publisherService.delete(id);
    }
}
