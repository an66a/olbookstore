package com.g2academy.olbookstore.service;

import com.g2academy.olbookstore.mapper.WarehouseBookMapper;
import com.g2academy.olbookstore.model.Book;
import com.g2academy.olbookstore.model.Warehouse;
import com.g2academy.olbookstore.model.WarehouseBook;
import com.g2academy.olbookstore.repository.BookRepo;
import com.g2academy.olbookstore.repository.WHBookRepo;
import com.g2academy.olbookstore.repository.WarehouseRepo;
import com.g2academy.olbookstore.service.dto.WarehouseBookDto;
import com.g2academy.olbookstore.service.response.CustomResponse;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseBookService {

    private final WHBookRepo whBookRepo;
    private final WarehouseRepo warehouseRepo;
    private final BookRepo bookRepo;

    WarehouseBookService(WHBookRepo whBookRepo, WarehouseRepo warehouseRepo, BookRepo bookRepo) {
        this.whBookRepo = whBookRepo;
        this.warehouseRepo = warehouseRepo;
        this.bookRepo = bookRepo;
    }

    private WarehouseBook save(WarehouseBook entity){
        return whBookRepo.save(entity);
    }

    public ResponseEntity<List<WarehouseBookDto>> findAll(){
        return ResponseEntity.ok(WarehouseBookMapper.INSTANCE.toDtos(whBookRepo.findAll()));
    }

    public ResponseEntity<?> add(WarehouseBookDto whBookDto){
        Warehouse warehouse = warehouseRepo.findById(whBookDto.getWarehouseId()).orElse(null);
        if (warehouse == null) return new CustomResponse().warehouseNotFound();

        Book book = bookRepo.findById(whBookDto.getBookId()).orElse(null);
        if (book == null) return  new CustomResponse().bookNotFound();

        WarehouseBook whEntity =WarehouseBook.builder()
                .warehouse(warehouse)
                .book(book)
                .quantity(whBookDto.getQuantity())
                .build();

        return ResponseEntity.ok(WarehouseBookMapper.INSTANCE.toDto(this.save(whEntity)));
    }

    public ResponseEntity<?> update(Long id, WarehouseBookDto whBookDto){
        WarehouseBook whBookEntity = whBookRepo.findById(id).orElse(null);
        if (whBookEntity == null) return new CustomResponse().whBookNotFound();

        if (whBookDto.getWarehouseId() != null) {
            Warehouse warehouse = warehouseRepo.findById(whBookDto.getWarehouseId()).orElse(null);
            if (warehouse == null) return new CustomResponse().warehouseNotFound();
            whBookEntity.setWarehouse(warehouse);
        }

        if (whBookDto.getBookId() != null) {
            Book book = bookRepo.findById(whBookDto.getBookId()).orElse(null);
            if (book == null) return new CustomResponse().bookNotFound();
            whBookEntity.setBook(book);
        }

        if (whBookDto.getQuantity() != null){
            whBookEntity.setQuantity(whBookDto.getQuantity());
        }
        return ResponseEntity.ok(WarehouseBookMapper.INSTANCE.toDto(this.save(whBookEntity)));
    }

    public void delete (Long id){
        this.whBookRepo.deleteById(id);
    }

    public ResponseEntity<List<WarehouseBookDto>> all(){
        return ResponseEntity.ok(WarehouseBookMapper.INSTANCE.toDtos(whBookRepo.findAll(Sort.by(Sort.Direction.ASC, "id"))));
    }

    public ResponseEntity<?> findById(Long id){
        WarehouseBook warehouseBook = whBookRepo.findById(id).orElse(null);
        if (warehouseBook == null) return  new CustomResponse().whBookNotFound();
        return ResponseEntity.ok(WarehouseBookMapper.INSTANCE.toDto(warehouseBook));
    }

    public ResponseEntity<List<WarehouseBookDto>>  findByBookIsbn(String isbn) {
        return ResponseEntity.ok(WarehouseBookMapper.INSTANCE.toDtos(whBookRepo.findByBook_Isbn(isbn)));
    }

    public ResponseEntity<List<WarehouseBookDto>>  findByBookTitle(String title) {
        return ResponseEntity.ok(WarehouseBookMapper.INSTANCE.toDtos(whBookRepo.findByBook_Title(title)));
    }

    public ResponseEntity<List<WarehouseBookDto>>  findByBookAuthorId(Long id) {
        return ResponseEntity.ok(WarehouseBookMapper.INSTANCE.toDtos(whBookRepo.findByBook_Author_Id(id)));
    }

    public ResponseEntity<List<WarehouseBookDto>> findByBookPublisherId(Long id) {
        return ResponseEntity.ok(WarehouseBookMapper.INSTANCE.toDtos(whBookRepo.findByBook_Publisher_Id(id)));
    }

    public ResponseEntity<List<WarehouseBookDto>> findByWarehouseId(Long id) {
        return ResponseEntity.ok(WarehouseBookMapper.INSTANCE.toDtos(whBookRepo.findByWarehouse_Id(id)));
    }
}
