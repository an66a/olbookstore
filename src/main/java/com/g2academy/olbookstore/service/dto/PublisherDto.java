package com.g2academy.olbookstore.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublisherDto {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String url;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<BookDto> books;
}
