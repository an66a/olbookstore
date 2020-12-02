package com.g2academy.olbookstore.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
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
@AuditTable("transactions_audit")
@Table(name = "transaction")
public class Transaction extends BaseEntity<String> implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TransactionItem> transactionItems;

    public void addTransactionItem(TransactionItem item) {
        if (this.transactionItems == null) this.transactionItems = new ArrayList<>();
        this.transactionItems.add(item);
        item.setTransaction(this);
    }

    //mapped to transactionDto totalTransactionPrice
    @Transactional
    public Double getTotalTransactionPrice() {
        Double total = .0;
        for (TransactionItem transactionItem : transactionItems) {
            total += transactionItem.getTotalPrice();
        }
        return total;
    }

    @Transactional
    public TransactionItem getTransactionItemById(Long id) {
        TransactionItem transactionItem = null;
        for (int i = 0; i < transactionItems.size(); i++){
            if (transactionItems.get(i).getId().equals(id)){
                transactionItem = transactionItems.get(i);
                break;
            }
        }
        return transactionItem;
    }
}
