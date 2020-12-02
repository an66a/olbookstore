package com.g2academy.olbookstore.service;

import com.g2academy.olbookstore.mapper.WarehouseBookMapper;
import com.g2academy.olbookstore.mapper.WarehouseMapper;
import com.g2academy.olbookstore.model.Book;
import com.g2academy.olbookstore.model.Warehouse;
import com.g2academy.olbookstore.model.WarehouseBook;
import com.g2academy.olbookstore.repository.BookRepo;
import com.g2academy.olbookstore.repository.WarehouseRepo;
import com.g2academy.olbookstore.service.dto.WarehouseBookDto;
import com.g2academy.olbookstore.service.dto.WarehouseDto;
import com.g2academy.olbookstore.service.response.CustomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    private final WarehouseRepo warehouseRepo;

    private final BookRepo bookRepo;

    WarehouseService(WarehouseRepo warehouseRepo, BookRepo bookRepo) {
        this.warehouseRepo = warehouseRepo;
        this.bookRepo = bookRepo;
    }

    private Warehouse save(Warehouse entity) {
        return this.warehouseRepo.save(entity);
    }

    public ResponseEntity<List<WarehouseDto>> findAll() {
        return ResponseEntity.ok(WarehouseMapper.INSTANCE.toDtos(warehouseRepo.findAll()));
    }

    public ResponseEntity<?> findById(Long warehouseId) {
        Warehouse warehouse = warehouseRepo.findById(warehouseId).orElse(null);
        if (warehouse == null) return new CustomResponse().warehouseNotFound();
        return ResponseEntity.ok(WarehouseMapper.INSTANCE.toDto(warehouse));
    }

    public ResponseEntity<?> getBooks(Long warehouseId) {
        Warehouse warehouse = warehouseRepo.findById(warehouseId).orElse(null);
        if (warehouse == null) return new CustomResponse().warehouseNotFound();
        return ResponseEntity.ok(WarehouseBookMapper.INSTANCE.toDtos(warehouse.getWh_books()));
    }

    public ResponseEntity<?> findByCodename(String codename) {
        Warehouse warehouse = warehouseRepo.findByCodename(codename).orElse(null);
        if (warehouse == null) return new CustomResponse().warehouseNotFound();
        return ResponseEntity.ok(WarehouseMapper.INSTANCE.toDto(warehouse));
    }

    public ResponseEntity<?> add(WarehouseDto warehouseDto) {
        Warehouse warehouseEntity = Warehouse.builder()
                .codename(warehouseDto.getCodename())
                .address(warehouseDto.getAddress())
                .phone(warehouseDto.getPhone())
                .build();
        if (warehouseDto.getWh_books() != null) {
            for (WarehouseBookDto whBookDto : warehouseDto.getWh_books()) {
                WarehouseBook whBookEntity = WarehouseBook.builder()
                        .quantity(whBookDto.getQuantity())
                        .build();
                Book book = bookRepo.findById(whBookDto.getBookId()).orElse(null);
                if (book == null) return new CustomResponse().bookNotFound();
                whBookEntity.setBook(book);
                warehouseEntity.addWHBook(whBookEntity);
            }
        }
        return ResponseEntity.ok(this.save(warehouseEntity));
    }

    public ResponseEntity<?> addWarehouseBook(Long warehouseId, WarehouseBookDto whBookDto) {
        Book book = bookRepo.findById(whBookDto.getBookId()).orElse(null);
        if (book == null) return new CustomResponse().bookNotFound();
        Warehouse warehouse = warehouseRepo.findById(warehouseId).orElse(null);
        if (warehouse == null) return new CustomResponse().warehouseNotFound();
        WarehouseBook whBook = warehouse.getWhBookByBookId(whBookDto.getBookId());
        if (whBook == null) {
            WarehouseBook whBookEntity = WarehouseBook.builder()
                    .book(book)
                    .quantity(whBookDto.getQuantity())
                    .build();
            warehouse.addWHBook(whBookEntity);
        } else {
            whBook.setQuantity(whBookDto.getQuantity());
        }
        return ResponseEntity.ok(this.save(warehouse));
    }

    public ResponseEntity<?> update(Long id, WarehouseDto warehouseDto) {
        Warehouse warehouseEntity = warehouseRepo.findById(id).orElse(null);
        if (warehouseEntity == null) return new CustomResponse().warehouseNotFound();
        if (warehouseDto.getCodename() != null) warehouseEntity.setCodename(warehouseDto.getPhone());
        if (warehouseDto.getAddress() != null) warehouseEntity.setAddress(warehouseDto.getAddress());
        if (warehouseDto.getPhone() != null) warehouseEntity.setPhone(warehouseDto.getPhone());

        if (warehouseDto.getWh_books() != null) { //check if there are update for boockstock
            for (WarehouseBookDto whBookDto : warehouseDto.getWh_books()) {
                for (WarehouseBook whBookEntity : warehouseEntity.getWh_books()) {
                    if (whBookDto.getId().equals(whBookEntity.getId())) {
                        whBookEntity.setQuantity(whBookDto.getQuantity());
                    }
                }
            }
        }
        return ResponseEntity.ok(this.save(warehouseEntity));
    }

    public void deleteWarehouse(Long id) {
        this.warehouseRepo.deleteById(id);
    }
}
