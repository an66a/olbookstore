package com.g2academy.olbookstore.mapper;

import com.g2academy.olbookstore.model.Author;
import com.g2academy.olbookstore.service.dto.AuthorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    @Mapping(target = "books", ignore = true)
    AuthorDto toDto(Author entity);

    Author toEntity(AuthorDto dto);

    List<AuthorDto> toDtos(List<Author> entities);

    List<Author> toEntities(List<AuthorDto> dtos);

//    @Named("listBookToDto")
//    static List<BookDto> listBookToDto(List<Book> books) {
//        return BookMapper.INSTANCE.toDtosWithoutAuthorId(books);
//    }
}
