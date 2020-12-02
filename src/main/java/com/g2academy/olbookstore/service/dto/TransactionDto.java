package com.g2academy.olbookstore.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long customerId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String customerName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String customerPhone;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String customerEmail;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String customerAddress;
    private List<TransactionItemDto> transactionItems;
    private Long totalTransactionPrice;
}
