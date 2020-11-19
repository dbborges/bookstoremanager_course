package com.danielborges.bookstoremanager.authors.service;

import com.danielborges.bookstoremanager.authors.controller.AuthorControllerDocs;
import com.danielborges.bookstoremanager.authors.dto.AuthorDTO;
import com.danielborges.bookstoremanager.authors.entity.Author;
import com.danielborges.bookstoremanager.authors.exception.AuthorAlreadyExistsException;
import com.danielborges.bookstoremanager.authors.mapper.AuthorMapper;
import com.danielborges.bookstoremanager.authors.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final static AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public AuthorDTO create(AuthorDTO authorDTO){
        verifyIfExists(authorDTO.getName());

        Author authorToCreate = authorMapper.toModel(authorDTO);
        Author createdAuthor = authorRepository.save(authorToCreate);
        return authorMapper.toDTO(createdAuthor);
    }

    private void verifyIfExists(String authorName) {
        authorRepository.findByName(authorName)
                .ifPresent(author -> {throw new AuthorAlreadyExistsException(authorName);});
    }

}
