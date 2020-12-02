package com.g2academy.olbookstore.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String url;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<BookDto> books;
}
