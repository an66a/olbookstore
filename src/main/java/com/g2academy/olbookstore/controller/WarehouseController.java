package com.g2academy.olbookstore.controller;

import com.g2academy.olbookstore.service.WarehouseService;
import com.g2academy.olbookstore.service.dto.WarehouseBookDto;
import com.g2academy.olbookstore.service.dto.WarehouseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public ResponseEntity<List<WarehouseDto>> all() {
        return warehouseService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id){
        return warehouseService.findById(id);
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<?> getBooks(@PathVariable("id") Long id){
        return warehouseService.getBooks(id);
    }

    @GetMapping(params = "codename")
    public ResponseEntity<?> findByCodename(@RequestParam String codename){
        return warehouseService.findByCodename(codename);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid WarehouseDto warehouseDto) {
        return warehouseService.add(warehouseDto);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> addWarehouseBook(@PathVariable("id") Long id, @RequestBody @Valid WarehouseBookDto warehouseBookDto) {
        return warehouseService.addWarehouseBook(id, warehouseBookDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWarehouse(@PathVariable("id") Long id, @RequestBody @Valid WarehouseDto warehouseDto) {
        return warehouseService.update(id, warehouseDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        warehouseService.deleteWarehouse(id);
    }
}
