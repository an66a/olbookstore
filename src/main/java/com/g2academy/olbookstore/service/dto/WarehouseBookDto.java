package com.g2academy.olbookstore.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseBookDto {
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long warehouseId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long bookId;
    private BookDto book;
    private Integer quantity;
}
