package com.g2academy.olbookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Audited
@AuditTable("transactionItems_audit")
@Table(name = "transactionItem")
public class TransactionItem extends BaseEntity<String> implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transaction_id")
    @JsonBackReference
    private Transaction transaction;

    @OneToOne
    private Book book;

    private Integer quantity;

    //mapped to transactionItemDto totalPrice
    @Transactional
    public Double getTotalPrice() {
        return book.getPrice() * getQuantity();
    }
}
