package com.g2academy.olbookstore.mapper;


import com.g2academy.olbookstore.model.Customer;
import com.g2academy.olbookstore.service.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    
    @Mapping(target = "transactions", ignore = true)
    CustomerDto toDto(Customer entity);

    Customer toEntity(CustomerDto dto);

    List<CustomerDto> toDtos(List<Customer> entities);

    List<Customer> toEntities(List<CustomerDto> dtos);

//    @Named("listTransactionToDto")
//    static List<TransactionDto> listTransactionToDto(List<Transaction> transactions) {
//        return TransactionMapper.INSTANCE.toDtosWithoutCustomerId(transactions);
//    }
}
