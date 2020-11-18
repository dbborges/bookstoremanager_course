package com.danielborges.bookstoremanager.authors.mapper;

import com.danielborges.bookstoremanager.authors.dto.AuthorDTO;
import com.danielborges.bookstoremanager.authors.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    Author toModel(AuthorDTO authorDTO);

    AuthorDTO toDTO(Author author);
}
