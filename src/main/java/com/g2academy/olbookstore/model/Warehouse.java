package com.g2academy.olbookstore.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Audited
@AuditTable("warehouses_audit")
@Table(name = "warehouses")
public class Warehouse extends BaseEntity<String> implements Serializable {

    @NotBlank
    private String codename;

    @NotBlank
    private String address;

    @NotBlank
    private String phone;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<WarehouseBook> wh_books;

    public void addWHBook (WarehouseBook warehouseBook){
        if (wh_books == null) this.wh_books = new ArrayList<>();
        this.wh_books.add(warehouseBook);
        warehouseBook.setWarehouse(this);
    }
}
