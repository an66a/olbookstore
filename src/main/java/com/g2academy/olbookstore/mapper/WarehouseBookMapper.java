package com.g2academy.olbookstore.mapper;

import com.g2academy.olbookstore.model.Book;
import com.g2academy.olbookstore.model.WarehouseBook;
import com.g2academy.olbookstore.service.dto.BookDto;
import com.g2academy.olbookstore.service.dto.WarehouseBookDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WarehouseBookMapper {

    WarehouseBookMapper INSTANCE = Mappers.getMapper(WarehouseBookMapper.class);

    @Mapping(source = "warehouse.id", target = "warehouseId")
    @Mapping(source = "book", target = "book", qualifiedByName = "bookToDto")
    WarehouseBookDto toDto(WarehouseBook entity);
    WarehouseBook toEntity(WarehouseBookDto dto);

    List<WarehouseBookDto> toDtos(List<WarehouseBook> entities);
    List<WarehouseBook> toEntities(List<WarehouseBookDto> dtos);

    @Named("mapWithoutWarehouseId")
    @Mapping(source = "book", target = "book", qualifiedByName = "bookToDto")
    WarehouseBookDto toDtoWithoutWarehouseId(WarehouseBook entity);

    @IterableMapping(qualifiedByName="mapWithoutWarehouseId")
    List<WarehouseBookDto> toDtosWithoutWarehouseId(List<WarehouseBook> entities);

    @Named("bookToDto")
    static BookDto bookToDto(Book book) {
        return BookMapper.INSTANCE.toDto(book);
    }
}
