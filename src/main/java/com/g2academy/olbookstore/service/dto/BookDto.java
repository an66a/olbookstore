package com.g2academy.olbookstore.service.dto;

import lombok.*;

import java.time.YearMonth;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;
    private Long authorId;
    private Long publisherId;
    private String authorName;
    private String publisherName;
    private String isbn;
    private YearMonth publishedOn;
    private String title;
    private Double price;
}
