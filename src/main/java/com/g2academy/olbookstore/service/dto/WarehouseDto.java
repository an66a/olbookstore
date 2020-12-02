package com.g2academy.olbookstore.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDto {
    private Long id;
    private String codename;
    private String address;
    private String phone;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<WarehouseBookDto> wh_books;
}
