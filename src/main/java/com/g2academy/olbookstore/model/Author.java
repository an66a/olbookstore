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
@AuditTable("authors_audit")
@Table(name = "authors")
public class Author extends BaseEntity<String> implements Serializable {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    @NotBlank
    private String address;

    @NotBlank
    private String url;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Book> books;

    public void addBook(Book book) {
        if(this.books == null ) this.books = new ArrayList<>();
        this.books.add(book);
        book.setAuthor(this);
    }

    //source = author.name on mapper
    public String getName(){
        return firstName + ' ' + lastName;
    }
}
