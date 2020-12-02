package com.g2academy.olbookstore.repository;

import com.g2academy.olbookstore.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehouseRepo extends JpaRepository<Warehouse, Long> {
    Optional<Warehouse> findByCodename(String codename);
}
