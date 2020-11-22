package com.danielborges.bookstoremanager.users.mapper;

import com.danielborges.bookstoremanager.authors.dto.AuthorDTO;
import com.danielborges.bookstoremanager.authors.entity.Author;
import com.danielborges.bookstoremanager.authors.mapper.AuthorMapper;
import com.danielborges.bookstoremanager.users.dto.UserDTO;
import com.danielborges.bookstoremanager.users.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toModel(UserDTO userDTO);

    UserDTO toDTO(User user);
}
