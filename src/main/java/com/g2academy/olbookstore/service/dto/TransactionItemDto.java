package com.g2academy.olbookstore.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionItemDto {
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long transactionId;
    private Long bookId;
    private Double bookPrice;
    private Integer quantity;
    private Long totalPrice;
}
