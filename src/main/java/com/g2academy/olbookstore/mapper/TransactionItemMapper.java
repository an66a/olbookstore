package com.g2academy.olbookstore.mapper;


import com.g2academy.olbookstore.model.TransactionItem;
import com.g2academy.olbookstore.service.dto.TransactionItemDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TransactionItemMapper {

    TransactionItemMapper INSTANCE = Mappers.getMapper(TransactionItemMapper.class);

    @Mapping(source = "transaction.id", target = "transactionId")
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.price", target = "bookPrice")
    TransactionItemDto toDto(TransactionItem entity);

    TransactionItem toEntity(TransactionItemDto dto);

    List<TransactionItemDto> toDtos(List<TransactionItem> entities);

    List<TransactionItem> toEntities(List<TransactionItemDto> dtos);

    @Named("mapWithoutTransactionId")
    @Mapping(target = "transactionId", ignore = true)
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.price", target = "bookPrice")
    TransactionItemDto toDtoWithoutTrasactionId(TransactionItem entity);

    @IterableMapping(qualifiedByName = "mapWithoutTransactionId")
    List<TransactionItemDto> toDtosWithoutTrasactionId(List<TransactionItem> entities);
}
