package com.g2academy.olbookstore.mapper;

import com.g2academy.olbookstore.model.Transaction;
import com.g2academy.olbookstore.model.TransactionItem;
import com.g2academy.olbookstore.service.dto.TransactionDto;
import com.g2academy.olbookstore.service.dto.TransactionItemDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(source = "transactionItems", target = "transactionItems", qualifiedByName = "listTransItemToDto")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "customer.name", target = "customerName")
    @Mapping(source = "customer.phone", target = "customerPhone")
    @Mapping(source = "customer.email", target = "customerEmail")
    @Mapping(source = "customer.address", target = "customerAddress")
    TransactionDto toDto(Transaction entity);

    Transaction toEntity(TransactionDto dto);

    List<TransactionDto> toDtos(List<Transaction> entities);

    List<Transaction> toEntities(List<TransactionDto> dtos);

    @Named("mapWithoutCustomerId")
    @Mapping(source = "transactionItems", target = "transactionItems", qualifiedByName = "listTransItemToDto")
    TransactionDto toDtoWithoutCustomerDetail(Transaction entity);

    @IterableMapping(qualifiedByName = "mapWithoutCustomerId")
    List<TransactionDto> toDtosWithoutCustomerDetail(List<Transaction> entities);


    @Named("listTransItemToDto")
    static List<TransactionItemDto> listTransItemToDto(List<TransactionItem> items) {
        return TransactionItemMapper.INSTANCE.toDtosWithoutTrasactionId(items);
    }
}
