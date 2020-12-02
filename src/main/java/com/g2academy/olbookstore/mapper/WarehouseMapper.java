package com.g2academy.olbookstore.mapper;

import com.g2academy.olbookstore.model.Warehouse;
import com.g2academy.olbookstore.model.WarehouseBook;
import com.g2academy.olbookstore.service.dto.WarehouseBookDto;
import com.g2academy.olbookstore.service.dto.WarehouseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WarehouseMapper {

    WarehouseMapper INSTANCE = Mappers.getMapper(WarehouseMapper.class);

    @Mapping(target = "wh_books", ignore = true)
    WarehouseDto toDto(Warehouse entity);

    @Named("mapWithBooks")
    @Mapping(source = "wh_books", target = "wh_books", qualifiedByName = "listWhBookToDto")
    WarehouseDto toDtoWithBooks(Warehouse entity);

    Warehouse toEntity(WarehouseDto dto);

    List<WarehouseDto> toDtos(List<Warehouse> entities);

    List<Warehouse> toEntities(List<WarehouseDto> dtos);

    @Named("listWhBookToDto")
    static List<WarehouseBookDto> listWhBookToDto(List<WarehouseBook> whBooks) {
        return WarehouseBookMapper.INSTANCE.toDtosWithoutWarehouseId(whBooks);
    }
}
