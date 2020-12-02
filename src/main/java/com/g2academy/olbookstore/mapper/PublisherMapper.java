package com.g2academy.olbookstore.mapper;

import com.g2academy.olbookstore.model.Publisher;
import com.g2academy.olbookstore.service.dto.PublisherDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PublisherMapper {

    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

    @Mapping(target = "books", ignore = true)
    PublisherDto toDto(Publisher entity);

    Publisher toEntity(PublisherDto dto);

    List<PublisherDto> toDtos(List<Publisher> entities);

    List<Publisher> toEntities(List<PublisherDto> dtos);

//    @Named("listBookToDto")
//    static List<BookDto> listBookToDto(List<Book> books) {
//        return BookMapper.INSTANCE.toDtosWithoutPublisheriId(books);
//    }
}
