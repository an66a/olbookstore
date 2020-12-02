package com.g2academy.olbookstore.mapper;

import com.g2academy.olbookstore.model.Book;
import com.g2academy.olbookstore.service.dto.BookDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "publisher.id", target = "publisherId")
    @Mapping(source = "author.name", target = "authorName")
    @Mapping(source = "publisher.name", target = "publisherName")
    BookDto toDto(Book entity);

    Book toEntity(BookDto dto);

    List<BookDto> toDtos(List<Book> entities);

    List<Book> toEntities(List<BookDto> dtos);

//    @Named("mapWithoutPublisheriId")
//    @Mapping(source = "author.id", target = "authorId")
//    @Mapping(target = "publisherId", ignore = true)
//    BookDto toDtoWithoutPublisheriId(Book entity);
//
//    @IterableMapping(qualifiedByName = "mapWithoutPublisheriId")
//    List<BookDto> toDtosWithoutPublisheriId(List<Book> entities);
//
//    @Named("mapWithoutAuthorId")
//    @Mapping(target = "authorId", ignore = true)
//    @Mapping(source = "publisher.id", target = "publisherId")
//    BookDto toDtoWithoutAuthorId(Book entity);
//
//    @IterableMapping(qualifiedByName = "mapWithoutAuthorId")
//    List<BookDto> toDtosWithoutAuthorId(List<Book> entities);
}
