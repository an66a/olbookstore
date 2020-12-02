package com.g2academy.olbookstore.repository;

import com.g2academy.olbookstore.model.WarehouseBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WHBookRepo extends JpaRepository<WarehouseBook, Long> {
    List<WarehouseBook> findByWarehouse_Id(Long warehouseId);
    List<WarehouseBook> findByBook_Title(String title);
    List<WarehouseBook> findByBook_Isbn(String isbn);
    List<WarehouseBook> findByBook_Author_Id(Long authorId);
    List<WarehouseBook> findByBook_Publisher_Id(Long publisherId);
}
