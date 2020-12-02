package com.g2academy.olbookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.g2academy.olbookstore.converter.YearMonthDateAttributeConverter;
import lombok.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.YearMonth;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Audited
@AuditTable("books_audit")
@Table(name = "books")
public class Book extends BaseEntity<String> implements Serializable {

    @NotBlank
    private String isbn;

    @Convert(converter = YearMonthDateAttributeConverter.class)
    private YearMonth publishedOn;

    @NotBlank
    private String title;

    private Double price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "publisher_id")
    @JsonBackReference
    private Publisher publisher;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private Author author;
}
