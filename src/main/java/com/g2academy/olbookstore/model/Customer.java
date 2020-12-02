package com.g2academy.olbookstore.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Audited
@AuditTable("customers_audit")
@Table(name = "customers")
public class Customer extends BaseEntity<String> implements Serializable {

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotBlank
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Transaction> transactions;

    public void addTransaction(Transaction transaction) {
        if(this.transactions == null) this.transactions = new ArrayList<>();
        this.transactions.add(transaction);
        transaction.setCustomer(this);
    }
}
