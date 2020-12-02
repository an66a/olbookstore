package com.g2academy.olbookstore.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String email;
    private String name;
    private String phone;
    private String address;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TransactionDto> transactions;
}
