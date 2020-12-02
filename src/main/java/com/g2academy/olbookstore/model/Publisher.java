package com.g2academy.olbookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@AuditTable("publishers_audit")
@Table(name = "publishers")
public class Publisher extends BaseEntity<String> implements Serializable {

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String phone;

    @NotBlank
    private String url;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Book> books;

    public void addBook(Book book) {
        if(this.books == null ) this.books = new ArrayList<>();
        this.books.add(book);
        book.setPublisher(this);
    }
}
